import java.util.ArrayList;

public class SamMclCPU {
    public static void main(String[] args) {
        idleTimes();
        averageIdleTimes();
        averageSystemTimes();
        averageUserTimes();
        utilisationTime();
        cpuName();
        socketCount();
        coreCount();
        logicalCoreCount();
        baseSpeed();
    }

    public static String cpuName() {
        cpuInfo cpu = new cpuInfo();
        cpu.read(0);
        return cpu.getModel();
    }

    public static int socketCount() {
        cpuInfo cpu = new cpuInfo();
        cpu.read(0);
        return cpu.socketCount();
    }

    public static int coreCount() {
        cpuInfo cpu = new cpuInfo();
        cpu.read(0);
        return cpu.coresPerSocket();
    }

    public static int logicalCoreCount() {
        cpuInfo cpu = new cpuInfo();
        cpu.read(0);

        String model = cpu.getModel();

        if (model.length() == 42) { // if the computer has over 10,000 handles then execute this code (this is to know if hyperthreading is used or not)
            String subModel = model.substring(33, 37); // These lines get the first two numbers of the handles to check if its over 13,000 as if it is then hyperthreading is not used
            Integer subNum = Integer.valueOf(subModel);
            if (subNum >= 13) {
                return cpu.coresPerSocket();
            }
        }
        else {
            return (cpu.coresPerSocket() * 2); // Hyperthreading is used and therefore the number of logical cores is doubled
        }
        return 0;
    }

    public static String baseSpeed() {
        cpuInfo cpu = new cpuInfo();
        cpu.read(0);
        String model = cpu.getModel(); // getting the model as it tells us the base speed at the end of the string

        // if statement used here as if the handles are above 10,000 then it will have 1 more index and therefore the base speed will be at a different index
        if (model.length() == 42) {
            String baseSpeed = model.substring(33, 38);
            return baseSpeed;
        }
        else{
            String baseSpeed1 = model.substring(32, 37);
            return baseSpeed1;
        }
    }

    // Individual Calls for formatting reasons
    public static double cacheSizel1d() {
        cpuInfo cpu = new cpuInfo();
        cpu.read(0);
        return (double) cpu.l1dCacheSize() * 0.001; // times by 0.001 for kilobytes
    }

    public static double cacheSizel1i() {
        cpuInfo cpu = new cpuInfo();
        cpu.read(0);
        return (double) cpu.l1iCacheSize() * 0.001; // times by 0.001 for kilobytes
    }

    public static double cacheSizel1() {
        cpuInfo cpu = new cpuInfo();
        cpu.read(0);
        return (double) cpu.l1iCacheSize() * 0.001 + cpu.l1dCacheSize() * 0.001; // To get total L1 cache we add both
    }

    public static double cacheSizel2() {
        cpuInfo cpu = new cpuInfo();
        cpu.read(0);
        return (double) cpu.l2CacheSize() * 0.000001; // times by 0.000001 for kilobytes
    }

    public static double cacheSizel3() {
        cpuInfo cpu = new cpuInfo();
        cpu.read(0);
        return (double) cpu.l3CacheSize() * 0.000001; // times by 0.000001 for kilobytes
    }


    public static void idleTimes() {
        cpuInfo cpu = new cpuInfo();

        // Sleep for 1 second and display the idle time percentage for
        // core 1.  This assumes 10Hz so in one second we have 100
        //cpu.read(1);

        // Idle times for all cores
        for (int i = 0; i < cpu.coresPerSocket(); i++) { // Loops through cores to get the different information from each
            //System.out.println("core" + i + "idle=" + cpu.getIdleTime(i) + "%");
            //System.out.println("core" + i + "active=" + (100 - cpu.getIdleTime(i) + "%"));
        }
    }

    public static double averageIdleTimes() {
        cpuInfo cpu = new cpuInfo();
        //cpu.read(1);

        //Average idle time
        ArrayList<Integer> idleTimeArray = new ArrayList<>(); // adds all the idle times to a single array
        for (int i = 0; i < cpu.coresPerSocket(); i++) {
            idleTimeArray.add(cpu.getIdleTime(i));
            //System.out.println(cpu.getIdleTime(i));
        }
        double sum = 0;
        for (int a = 0; a < cpu.coresPerSocket(); a++) {
            sum += idleTimeArray.get(a);
        }
        double idleTimeAverage = sum / cpu.coresPerSocket();
        //System.out.println(idleTimeAverage);

        return idleTimeAverage;
    }

    public static double averageUserTimes() {
        cpuInfo cpu = new cpuInfo();
        //cpu.read(1);

        // Average user time
        ArrayList<Integer> userTimeArray = new ArrayList<>(); // Same as idle times above
        for (int i = 0; i < cpu.coresPerSocket(); i++) {
            userTimeArray.add(cpu.getUserTime(i));
            //System.out.println(cpu.getUserTime(i));
        }

        double sum1 = 0;
        for (int a = 0; a < cpu.coresPerSocket(); a++) {
            sum1 += userTimeArray.get(a);
        }
        double userTimeAverage = sum1 / cpu.coresPerSocket();
        //System.out.println(userTimeAverage);

        return userTimeAverage;
    }

    public static double averageSystemTimes() {
        cpuInfo cpu = new cpuInfo();
        //cpu.read(1);

        // Average system time
        ArrayList<Integer> systemTimeArray = new ArrayList<>(); // Same as two above
        for (int i = 0; i < cpu.coresPerSocket(); i++) {
            systemTimeArray.add(cpu.getSystemTime(i));
            //System.out.println(cpu.getSystemTime(i));
        }

        double sum2 = 0;
        for (int a = 0; a < cpu.coresPerSocket(); a++) {
            sum2 += systemTimeArray.get(a);
        }
        double systemTimeAverage = sum2 / cpu.coresPerSocket();

        //System.out.println(systemTimeAverage);
        return systemTimeAverage;
    }

    public static double utilisationTime() {
        cpuInfo cpu = new cpuInfo();
        cpu.read(1);

        // Utilisation Average
        double utilisationAverage = 0;

        for (int j = 0; j < cpu.coresPerSocket(); j++) {
            //double utilisationPercentage = 100 - cpu.getIdleTime(j);
            double utilisationPercentage = ((double) (cpu.getUserTime(j) + cpu.getSystemTime(j)) / (cpu.getUserTime(j) + cpu.getSystemTime(j) + cpu.getIdleTime(j)) * 100);
            //System.out.println("Utilisation percentage for core " + j + " is " + utilisationPercentage + "%");
            utilisationAverage += utilisationPercentage; //adds all the utilisation percentages together for the average

        }
        return (double) utilisationAverage/cpu.coresPerSocket();
    }
}