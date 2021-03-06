package com.dreambird.quartz;


public class TestJob {

    public static String JOB_NAME = "动态任务调度";
    public static String TRIGGER_NAME = "动态任务触发器";
    public static String JOB_GROUP_NAME = "XLXXCC_JOB_GROUP";
    public static String TRIGGER_GROUP_NAME = "XLXXCC_JOB_GROUP";

    private static TestJob testJob = new TestJob();

    private TestJob(){}

    public static TestJob getTestJob(){
        return testJob;
    }

    public void test() {
        try {
            System.out.println("【系统启动】开始(每1秒输出一次)...");
            QuartzManager.addJob(JOB_NAME, JOB_GROUP_NAME, TRIGGER_NAME, TRIGGER_GROUP_NAME, MyJob.class, "0/1 * * * * ?");

            Thread.sleep(5000);
            System.out.println("【修改时间】开始(每5秒输出一次)...");
            QuartzManager.modifyJobTime(JOB_NAME, JOB_GROUP_NAME, TRIGGER_NAME, TRIGGER_GROUP_NAME, "0/5 * * * * ?");

            Thread.sleep(16000);
            System.out.println("【移除定时任务】开始...");
            QuartzManager.removeJob(JOB_NAME, JOB_GROUP_NAME, TRIGGER_NAME, TRIGGER_GROUP_NAME);
            System.out.println("【移除定时任务】成功");

            Thread.sleep(6000);
            System.out.println("【关闭定时器】开始...");
            QuartzManager.shutdownJobs();
            System.out.println("【关闭定时器】成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
