// This should work for memory

public class memory {

    private memInfo memory;

    public memory() {
        memory = new memInfo();

    }

    public void readMemory() {
        memory.read();
    }

    public int getTotalMemory() {
        return memory.getTotal();
    }

    public int getUsedMemory() {
        return memory.getUsed();
    }

    public int getFreeMemory() {
        return getTotalMemory() - getUsedMemory();
    }

    public int getMemoryAsAPercentage() {
        return (int) ((getUsedMemory() / (double) getTotalMemory()) * 100);
    }


    // 2d array
    public String[][] getMemoryInfoAs2DArray() {
        String[][] memoryInfo = new String[3][2];

        memoryInfo[0][0] = "Total Memory";
        memoryInfo[0][1] = String.valueOf(getTotalMemory());

        memoryInfo[1][0] = "Used Memory";
        memoryInfo[1][1] = String.valueOf(getUsedMemory());

        memoryInfo[2][0] = "Free Memory";
        memoryInfo[2][1] = String.valueOf(getFreeMemory());

        memoryInfo[3][0] = "Memory Used As A Percentage";
        memoryInfo[3][1] = String.valueOf(getMemoryAsAPercentage());

        return memoryInfo;
    }


}
