import java.util.ArrayList;

public class SamMclCPU {
    public static void main(String[] args) {
        modelEtc();
        cacheSizes();
        idleTimes();
        averageIdleTimes();
        averageSystemTimes();
        averageUserTimes();
        utilisationTime();
    }


    // TODO I need you to give me a call to get cores, logical cores and cpu information one by one like i did with the cache
    // Very Important do not use print statements and make sure return is not void I can't use it otherwise at all

    public static void modelEtc(){
        System.loadLibrary("sysInfo");
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
    }

    // Overall cache Size Call
    public static void cacheSizes(){
        System.loadLibrary("sysInfo");
        cpuInfo cpu = new cpuInfo();
        cpu.read(0);

        // Show sizes of L1,L2 and L3 cache
        System.out.println("l1d=" + (cpu.l1dCacheSize()*0.001) +
                ", l1i=" + (cpu.l1iCacheSize()*0.001) +
                ", l2=" + (cpu.l2CacheSize()*0.000001) +
                ", l3=" + (cpu.l3CacheSize()*0.000001));
    }

    // Individual Calls for formatting reasons
    public static int cacheSizel1d(){
        System.loadLibrary("sysInfo");
        cpuInfo cpu = new cpuInfo();
        cpu.read(0);
        return (int) Math.round(cpu.l1dCacheSize()*0.001);
    }

    public static int cacheSizel1i(){
        System.loadLibrary("sysInfo");
        cpuInfo cpu = new cpuInfo();
        cpu.read(0);
        return (int) Math.round(cpu.l1iCacheSize()*0.001);
    }

    public static int cacheSizel1(){
        System.loadLibrary("sysInfo");
        cpuInfo cpu = new cpuInfo();
        cpu.read(0);
        return (int) Math.round(cpu.l1iCacheSize()*0.001 + cpu.l1dCacheSize()*0.001); // To get total L1 cache we add both
    }

    public static int cacheSizel2(){
        System.loadLibrary("sysInfo");
        cpuInfo cpu = new cpuInfo();
        cpu.read(0);
        return (int) Math.round(cpu.l2CacheSize()*0.000001);
    }

    public static int cacheSizel3(){
        System.loadLibrary("sysInfo");
        cpuInfo cpu = new cpuInfo();
        cpu.read(0);
        return (int) Math.round(cpu.l3CacheSize()*0.000001);
    }


    public static void idleTimes (){
        System.loadLibrary("sysInfo");
        cpuInfo cpu = new cpuInfo();
        cpu.read(0);

        // Sleep for 1 second and display the idle time percentage for
        // core 1.  This assumes 10Hz so in one second we have 100
        cpu.read(1);

        // Idle times for all cores
        for (int i = 0; i < cpu.coresPerSocket(); i++){
            System.out.println("core" + i + "idle=" + cpu.getIdleTime(i) + "%");
            System.out.println("core" + i + "active=" + (100 - cpu.getIdleTime(i) + "%"));
        }
    }

    public static double averageIdleTimes(){
        System.loadLibrary("sysInfo");
        cpuInfo cpu = new cpuInfo();
        cpu.read(0);

        //Average idle time
        ArrayList<Integer> idleTimeArray = new ArrayList<>();
        for (int i = 0; i < cpu.coresPerSocket(); i++){
            idleTimeArray.add(cpu.getIdleTime(i));
            System.out.println(cpu.getIdleTime(i));
        }
        double sum = 0;
        for (int a = 0; a < cpu.coresPerSocket(); a++) {
            sum += idleTimeArray.get(a);
        }
        double idleTimeAverage = sum / cpu.coresPerSocket();
        //System.out.println(idleTimeAverage);

        return idleTimeAverage;
    }

    public static double averageUserTimes(){
        System.loadLibrary("sysInfo");
        cpuInfo cpu = new cpuInfo();
        cpu.read(0);

        // Average user time
        ArrayList<Integer> userTimeArray = new ArrayList<>();
        for (int i = 0; i < cpu.coresPerSocket(); i++){
            userTimeArray.add(cpu.getUserTime(i));
            System.out.println(cpu.getUserTime(i));
        }

        double sum1 = 0;
        for (int a = 0; a < cpu.coresPerSocket(); a++) {
            sum1 += userTimeArray.get(a);
        }
        double userTimeAverage = sum1 / cpu.coresPerSocket();
        //System.out.println(userTimeAverage);

        return userTimeAverage;
    }

    public static double averageSystemTimes(){
        System.loadLibrary("sysInfo");
        cpuInfo cpu = new cpuInfo();
        cpu.read(0);

        // Average system time
        ArrayList<Integer> systemTimeArray = new ArrayList<>();
        for (int i = 0; i < cpu.coresPerSocket(); i++){
            systemTimeArray.add(cpu.getSystemTime(i));
            System.out.println(cpu.getSystemTime(i));
        }

        double sum2 = 0;
        for (int a = 0; a < cpu.coresPerSocket(); a++) {
            sum2 += systemTimeArray.get(a);
        }
        double systemTimeAverage = sum2 / cpu.coresPerSocket();

        //System.out.println(systemTimeAverage);
        return systemTimeAverage;
    }

    public static double utilisationTime(){
        System.loadLibrary("sysInfo");
        cpuInfo cpu = new cpuInfo();
        cpu.read(0);

        // Utilisation Average
        ArrayList<Integer> utilisationAverage = new ArrayList<>();
        double sum3 = 0;

        for (int j = 0; j < cpu.coresPerSocket(); j++) {
            double utilisationPercentage = ((double)(cpu.getUserTime(j) + cpu.getSystemTime(j)) / (cpu.getUserTime(j) + cpu.getSystemTime(j) + cpu.getIdleTime(j)));
            System.out.println("Untilisation percentage for core " + j + " is " + utilisationPercentage + "%");

            utilisationAverage.add(cpu.getIdleTime(j));
            sum3 += utilisationPercentage;

        }
        //System.out.println(utilisationAverage);

        return sum3;
    }
}
