/*
 *  CPU information class for JNI
 *
 *  Copyright (c) 2024 Mark Burkley (mark.burkley@ul.ie)
 */

public class cpuInfo 
{
    static {
        System.loadLibrary("cpuInfoLib");
    }
    // Refresh the current values and counters - call this before other methods
    public native void read (int seconds);
    public native void read ();

    // Return the number of cores per CPU socket
    public native int coresPerSocket ();

    // Return the number of CPUs in this computer
    public native int socketCount ();

    // Return the model number of the CPU
    public native String getModel ();

    // Return the size in bytes of the L1 data cache
    public native int l1dCacheSize ();

    // Return the size in bytes of the L1 instruction cache
    public native int l1iCacheSize ();

    // Return the size in bytes of the L2 cache
    public native int l2CacheSize ();

    // Return the size in bytes of the L3 cache
    public native int l3CacheSize ();

    // Return the time in "jiffies" or 1/100ths of a second 
    // that the specified core has been in user mode
    public native int getUserTime (int core);

    // Return the time in "jiffies" or 1/100ths of a second 
    // that the specified core has been idle
    public native int getIdleTime (int core);

    // Return the time in "jiffies" or 1/100ths of a second 
    // that the specified core has been in system mode
    public native int getSystemTime (int core);

    public static void main(String[] args){
        cpuInfo info = new cpuInfo();

        System.out.println("CPU Model: " + info.getModel());
        System.out.println("Cores per Socket: " + info.coresPerSocket());
        System.out.println("Socket Count: " + info.socketCount());
        System.out.println("L1 Data Cache Size: " + info.l1dCacheSize() + " bytes");
        System.out.println("L1 Instruction Cache Size: " + info.l1iCacheSize() + " bytes");
        System.out.println("L2 Cache Size: " + info.l2CacheSize() + " bytes");
        System.out.println("L3 Cache Size: " + info.l3CacheSize() + " bytes");

        for (int core = 0; core < info.socketCount() * info.coresPerSocket(); core++) {
            System.out.println("Core " + core + " - User Time: " + info.getUserTime(core));
            System.out.println("Core " + core + " - System Time: " + info.getSystemTime(core));
            System.out.println("Core " + core + " - Idle Time: " + info.getIdleTime(core));
        }
    }

}
