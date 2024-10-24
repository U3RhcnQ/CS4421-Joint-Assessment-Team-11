
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
        System.out.println("l1d=" + cpu.l1dCacheSize() +
                ", l1i=" + cpu.l1iCacheSize() +
                ", l2=" + cpu.l2CacheSize() +
                ", l3=" + cpu.l3CacheSize());



        // Sleep for 1 second and display the idle time percentage for
        // core 1.  This assumes 10Hz so in one second we have 100
        cpu.read(1);
        System.out.println("core 1 idle=" + cpu.getIdleTime(1) + "%");
        System.out.println("core 1 active=" + (100 - cpu.getIdleTime(1) + "%"));

        if (cpu.coresPerSocket() == 2){
            System.out.println("core 2 idle=" + cpu.getIdleTime(2) + "%");
            System.out.println("core 2 active=" + (100 - cpu.getIdleTime(2) + "%"));
        }

        if (cpu.coresPerSocket() == 3){
            System.out.println("core 3 idle=" + cpu.getIdleTime(3) + "%");
            System.out.println("core 3 active=" + (100 - cpu.getIdleTime(3) + "%"));
        }

        if (cpu.coresPerSocket() == 4){
            System.out.println("core 4 idle=" + cpu.getIdleTime(4) + "%");
            System.out.println("core 4 active=" + (100 - cpu.getIdleTime(4) + "%"));
        }



    }
}
