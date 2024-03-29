package com.linkedin.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableBatchProcessing
public class LinkedinBatchApplication {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    public static void main(String[] args) {
        SpringApplication.run(LinkedinBatchApplication.class, args);
    }

    @Bean
    public JobExecutionDecider decider() {
        return new DeliveryDecider();
    }

    @Bean
    public JobExecutionDecider receiptDecider() {
        return new ReceiptDecider();
    }

    @Bean
    public StepExecutionListener selectFlowerListener() {
        return new FlowersSelectionStepExecutionListener();
    }

    @Bean
    public Step thankCustomerStep() {
        return this.stepBuilderFactory
                .get("thankCustomer")
                .tasklet(
                        new Tasklet() {
                            @Override
                            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                                System.out.println("Thanking the customer.");
                                return RepeatStatus.FINISHED;
                            }
                        }
                )
                .build();
    }

    @Bean
    public Step refundStep() {
        return this.stepBuilderFactory
                .get("refund")
                .tasklet(
                        new Tasklet() {
                            @Override
                            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                                System.out.println("Refunding customer money.");
                                return RepeatStatus.FINISHED;
                            }
                        }
                )
                .build();
    }

    @Bean
    public Step leaveAtDoorStep() {
        return this.stepBuilderFactory
                .get("leaveAtDoor")
                .tasklet(
                        new Tasklet() {
                            @Override
                            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                                System.out.println("Leaving the package at the door.");
                                return RepeatStatus.FINISHED;
                            }
                        }
                )
                .build();
    }

    @Bean
    public Step storePackageStep() {
        return this.stepBuilderFactory
                .get("storePackage")
                .tasklet(
                        new Tasklet() {
                            @Override
                            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                                System.out.println("Storing the package while the customer address is located.");
                                return RepeatStatus.FINISHED;
                            }
                        }
                )
                .build();
    }

    @Bean
    public Step givePackageToCustomerStep() {
        return this.stepBuilderFactory
                .get("givePackageToCustomer")
                .tasklet(
                        new Tasklet() {
                            @Override
                            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                                System.out.println("Given the package to the customer.");
                                return RepeatStatus.FINISHED;
                            }
                        }
                )
                .build();
    }

    @Bean
    public Step driveToAddressStep() {
        boolean gotLost = false;
        return this.stepBuilderFactory
                .get("driveToAddress")
                .tasklet(
                        new Tasklet() {
                            @Override
                            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

                                if (gotLost) {
                                    throw new RuntimeException("Got lost driving to the address");
                                }

                                System.out.println("Successfully arrived at address.");
                                return RepeatStatus.FINISHED;
                            }
                        }
                )
                .build();
    }

    @Bean
    public Step packageItemStep() {
        return this.stepBuilderFactory
                .get("packageItemStep")
                .tasklet(
                        new Tasklet() {
                            @Override
                            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                                String item = chunkContext.getStepContext()
                                                          .getJobParameters()
                                                          .get("item").toString();

                                String date = chunkContext.getStepContext()
                                                          .getJobParameters()
                                                          .get("run.date").toString();


                                System.out.println(String.format("The %s has been packaged on %s.", item, date));
                                return RepeatStatus.FINISHED;
                            }
                        }
                )
                .build();
    }

    @Bean
    public Step removeThornsStep(){
        return this.stepBuilderFactory.get("removeThornsStep")
                                      .tasklet(
                                              new Tasklet() {
                                                  @Override public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                                                      System.out.println("Remove thorns from roses.")
                                                      ;return RepeatStatus.FINISHED;
                                                  }
                                              }
                                      )
                                      .build();
    }

    @Bean
    public Step selectFlowersStep(){
        return this.stepBuilderFactory.get("selectFlowersStep")
                                      .tasklet(
                                              new Tasklet() {
                                                  @Override public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                                                      System.out.println("Gathering flowers for order.")
                                                      ;return RepeatStatus.FINISHED;
                                                  }
                                              }
                                      )
                                      .listener(selectFlowerListener())
                                      .build();
    }

    @Bean
    public Step arrangeFlowersStep(){
        return this.stepBuilderFactory.get("arrangeFlowersStep")
                .tasklet(
                        new Tasklet() {
                            @Override public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                                System.out.println("Arranging flowers for order.")
                                ;return RepeatStatus.FINISHED;
                            }
                        }
                )
                .build();
    }

    @Bean
    public Job deliverPackageJob() {
        return this.jobBuilderFactory
                .get("deliverPackageJob")
                .start(packageItemStep())
                .next(driveToAddressStep())
                    .on("FAILED")
                    .fail()
                .from(driveToAddressStep())
                    .on("*")
                    .to(decider())
                        .on("PRESENT")
                        .to(givePackageToCustomerStep())
                        .next(receiptDecider())
                            .on("CORRECT")
                            .to(thankCustomerStep())
                        .from(receiptDecider())
                            .on("INCORRECT")
                            .to(refundStep())
                    .from(decider())
                        .on("NOT_PRESENT")
                        .to(leaveAtDoorStep())
                .end()
                .build();
    }

    @Bean
    public Job prepareFlowers(){
        return this.jobBuilderFactory.get("prepareFlowersJob")
                .start(selectFlowersStep())
                    .on("TRIM_REQUIRED")
                    .to(removeThornsStep())
                    .next(arrangeFlowersStep())
                .from(selectFlowersStep())
                    .on("NO_TRIM_REQUIRED")
                .to(arrangeFlowersStep())
                .end()
                .build();
    }

}
