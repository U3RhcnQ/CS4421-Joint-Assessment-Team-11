import java.util.ArrayList;

public class SamDiskInfo {
    public static void main(String[] args){
        System.loadLibrary("sysinfo"); // PLease add the library it's important
        percentUsed();
        diskBlocks();
        diskNames();
        diskBlocksUsed();
    }
    public static double[] percentUsed(){
        diskInfo disk = new diskInfo();
        disk.read();
        double[] diskPercentageArray = new double[disk.diskCount()];
        for (int i = 0; i < disk.diskCount(); i++) {
            double percentageDisk = ((double)disk.getUsed(i) / disk.getTotal(i))*100;
            diskPercentageArray[i] = percentageDisk;
        }
        return diskPercentageArray;
    }
    public static ArrayList<String> diskNames(){
        ArrayList<String> diskNamesArray = new ArrayList<>();
        diskInfo disk = new diskInfo();
        disk.read();
        for (int i = 0; i < disk.diskCount(); i++) {
            diskNamesArray.add(disk.getName(i));
        }
        return diskNamesArray;
    }
    public static long[] diskBlocks() {
        diskInfo disk = new diskInfo();
        disk.read();
        long[] diskBlocksArray = new long[disk.diskCount()];
        for (int i = 0; i < disk.diskCount(); i++) {
            diskBlocksArray[i] = disk.getTotal(i);
        }
        return diskBlocksArray;
    }
    public static long[] diskBlocksUsed() {
        diskInfo disk = new diskInfo();
        disk.read();
        long[] diskBlocksUsedArray = new long[disk.diskCount()];
        for (int i = 0; i < disk.diskCount(); i++) {
            diskBlocksUsedArray[i] = disk.getUsed(i);
        }
        return diskBlocksUsedArray;
    }
}