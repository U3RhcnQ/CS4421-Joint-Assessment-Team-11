import java.util.ArrayList;

public class SamMclCPU {
    public static void main(String[] args) {

        System.loadLibrary("cpuInfo");
        cpuInfo cpu = new cpuInfo();
        cpu.read(0);

        String model = cpu.getModel();
        Integer cpuCores = cpu.coresPerSocket();


        if(model.length() == 39){
            String subModel = model.substring(31,34);
            String baseSpeed = model.substring(31,38);
            Integer subNum = Integer.valueOf(subModel);
            if(subNum >= 13){
                System.out.println("CPU " + cpu.getModel() + " has " +
                        cpu.socketCount() + " sockets each with " +
                        cpu.coresPerSocket() + " cores" + cpu.coresPerSocket() + "logical cores and a base speed of " + baseSpeed);
            }
        }
        else{
            // Show CPU model, CPU sockets and cores per socket
            String baseSpeed1 = model.substring(30,37);
            System.out.println("CPU " + cpu.getModel() + " has " +
                    cpu.socketCount() + " sockets each with " +
                    cpu.coresPerSocket() + " cores" + (cpu.coresPerSocket()*2) + "logical cores, has hyperthreading enabled and a base speed of " + baseSpeed1);
        }



        // Show sizes of L1,L2 and L3 cache
        System.out.println("l1d=" + (cpu.l1dCacheSize()*0.001) +
                ", l1i=" + (cpu.l1iCacheSize()*0.001) +
                ", l2=" + (cpu.l2CacheSize()*0.000001) +
                ", l3=" + (cpu.l3CacheSize()*0.000001));



        // Sleep for 1 second and display the idle time percentage for
        // core 1.  This assumes 10Hz so in one second we have 100
        cpu.read(1);

        for (int i = 1; i <= cpu.coresPerSocket(); i++){
            System.out.println("core" + i + "idle=" + cpu.getIdleTime(i) + "%");
            System.out.println("core" + i + "active=" + (100 - cpu.getIdleTime(i) + "%"));
        }

        ArrayList<Integer> idleTimeArray = new ArrayList<>();
        for (int i = 1; i <= cpu.coresPerSocket(); i++){
            idleTimeArray.add(cpu.getIdleTime(i));
        }

        double sum = 0;
        for (int a = 0; a < cpu.coresPerSocket(); a++) {
            sum += idleTimeArray.get(a);
        }
        double idleTimeAverage = sum / cpu.coresPerSocket();

        ArrayList<Integer> userTimeArray = new ArrayList<>();
        for (int i = 1; i <= cpu.coresPerSocket(); i++){
            userTimeArray.add(cpu.getUserTime(i));
        }

        double sum1 = 0;
        for (int a = 0; a < cpu.coresPerSocket(); a++) {
            sum1 += userTimeArray.get(a);
        }
        double userTimeAverage = sum1 / cpu.coresPerSocket();

        ArrayList<Integer> systemTimeArray = new ArrayList<>();
        for (int i = 1; i <= cpu.coresPerSocket(); i++){
            systemTimeArray.add(cpu.getSystemTime(i));
        }

        double sum2 = 0;
        for (int a = 0; a < cpu.coresPerSocket(); a++) {
            sum2 += systemTimeArray.get(a);
        }
        double systemTimeAverage = sum2 / cpu.coresPerSocket();

        ArrayList<Integer> utilisationAverage = new ArrayList<>();
        for (int j = 1; j <= cpu.coresPerSocket(); j++) {
            double untilisationPercentage = ((cpu.getUserTime(1) + cpu.getSystemTime(j)) / (cpu.getUserTime(j) + cpu.getSystemTime(j) + cpu.getIdleTime(j)));
            System.out.println("Untilisation percentage for core " + j + " is " + untilisationPercentage + "%");
            utilisationAverage.add(cpu.getIdleTime(j));
        }

    }
}
