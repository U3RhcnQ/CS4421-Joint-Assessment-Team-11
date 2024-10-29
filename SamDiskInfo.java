import java.util.ArrayList;

public class SamDiskInfo {
    public static void main(String[] args){
        System.loadLibrary("sysinfo");
        diskTable();
    }
    /*
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
    public static long[] diskTotal() {
        diskInfo disk = new diskInfo();
        disk.read();
        long[] diskBlocksArray = new long[disk.diskCount()];
        for (int i = 0; i < disk.diskCount(); i++) {
            diskBlocksArray[i] = disk.getTotal(i);
        }
        return diskBlocksArray;
    }
    public static long[] diskUsed() {
        diskInfo disk = new diskInfo();
        disk.read();
        long[] diskBlocksUsedArray = new long[disk.diskCount()];
        for (int i = 0; i < disk.diskCount(); i++) {
            diskBlocksUsedArray[i] = disk.getUsed(i);
        }
        return diskBlocksUsedArray;
    }
    */
    public static String[][] diskTable(){
        diskInfo disk = new diskInfo();
        disk.read();
        String[][] diskInfoArray = new String[4][disk.diskCount()]; // Creates the 2d list
        for (int i = 0; i < disk.diskCount(); i++) {
            diskInfoArray[0][i] = disk.getName(i); // Adds the disk names to the first row
            diskInfoArray[1][i] = String.valueOf(disk.getTotal(i)); // Adds the total for each disk to the second row
            diskInfoArray[2][i] = String.valueOf(disk.getUsed(i)); // Adds the used storage to the third row
            diskInfoArray[3][i] = String.valueOf(((double)(disk.getUsed(i) / disk.getTotal(i)))*100); // Adds the percent used to the fourth row
        }
        return diskInfoArray;
    }
}