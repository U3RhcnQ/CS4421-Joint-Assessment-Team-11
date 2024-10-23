public class SamMclCPU {
    public static void main(String[] args) {

        System.loadLibrary("cpuInfo");
        cpuInfo cpu = new cpuInfo();
        cpu.read(0);

        // Show CPU model, CPU sockets and cores per socket
        System.out.println("CPU " + cpu.getModel() + " has " +
                cpu.socketCount() + " sockets each with " +
                cpu.coresPerSocket() + " cores");

        // Show sizes of L1,L2 and L3 cache
        System.out.println("l1d=" + cpu.l1dCacheSize() +
                ", l1i=" + cpu.l1iCacheSize() +
                ", l2=" + cpu.l2CacheSize() +
                ", l3=" + cpu.l3CacheSize());



        // Sleep for 1 second and display the idle time percentage for
        // core 1.  This assumes 10Hz so in one second we have 100
        cpu.read(1);
        System.out.println("core 1 idle=" + cpu.getIdleTime(1) + "%");



    }
}
