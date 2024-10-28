import java.util.ArrayList;

public class SamDiskInfo {
    public static void main(String[] args){
        System.loadLibrary("sysinfo"); // PLease add the library it's important
        percentUsed();
        diskBlocks();
        diskNames();
        diskBlocksUsed();
    }
    public static void percentUsed(){
        diskInfo disk = new diskInfo();
        disk.read();
        double[] diskPercentageArray = new double[disk.diskCount()];
        for (int i = 0; i < disk.diskCount(); i++) {
            double percentageDisk = ((double)disk.getUsed(i) / disk.getTotal(i))*100;
            diskPercentageArray[i] = percentageDisk;
        }
        System.out.println(diskPercentageArray);
    }
    public static void diskNames(){
        ArrayList<String> diskNamesArray = new ArrayList<>();
        diskInfo disk = new diskInfo();
        disk.read();
        for (int i = 0; i < disk.diskCount(); i++) {
            diskNamesArray.add(disk.getName(i));
        }
        System.out.println(diskNamesArray);
    }
    public static void diskBlocks() {
        diskInfo disk = new diskInfo();
        disk.read();
        long[] diskBlocksArray = new long[disk.diskCount()];
        for (int i = 0; i < disk.diskCount(); i++) {
            diskBlocksArray[i] = disk.getTotal(i);
        }
        System.out.println(diskBlocksArray);
    }
    public static void diskBlocksUsed() {
        diskInfo disk = new diskInfo();
        disk.read();
        long[] diskBlocksUsedArray = new long[disk.diskCount()];
        for (int i = 0; i < disk.diskCount(); i++) {
            diskBlocksUsedArray[i] = disk.getUsed(i);
        }
        System.out.println(diskBlocksUsedArray);
    }
}