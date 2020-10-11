package util;

/**
 * 命令解析器
 */
public class CommandResolver {
    //解析命令后将获取的有效值保存
    private int expCounts = 5;//默认值是5
    private int maxValue;

    /**
     * 解析命令行参数，思路如下：
     * 先获取参数数组的长度
     * 若是2和4之外的长度，说明输入格式错误；
     * 若是无 -r 参数，也是错误；
     * 若长度为2，则指定的参数是-r，获取-r之后的参数，解析。若解析错误，则发出提示信息，程序结束；
     * 若长度为4，则两个参数都有指定，获取[0]和[2]的位置的参数，判断是否是-n -r其中一个，若不是，发出提示信息，程序结束，
     * 若以上通过判断，若第一个参数是-n，则把[1] [3]位置是值分别解析为最大值，题目数；若第一个参数是-r，则反之。
     * @param command
     */
    public boolean resolve(String[] command){
        int len = command.length;
        //以合理形式输入指令，参数长度必是2或者4
        if(len != 2 && len != 4){
            System.out.println("请以 [-n expCount] -r maxValue 的形式（-n -r无次序要求，必须指定-r参数）输入指令");
            return false;
        }

        if(!hasRParam(command)){
            System.out.println("必须指定 -r 参数（指定最大值）");
            return false;
        }

        //长度为2，只指定了-r参数
        if(len==2){
            String maxStr = command[1];
            try {
                maxValue = Integer.parseInt(maxStr);
            }catch (NumberFormatException e){
                System.out.println("-n -r之后的参数应是整数");
                return  false;
            }
        }

        //长度为4，则两个参数都有指定
        if(len==4){
            String firstParam = command[0];
            String secondParam = command[2];
            String expStr = null;
            String maxStr = null;

            //两个参数有一个不是 -n和-r 其中的一个，则提示，程序结束
            if((!("-n".equals(firstParam)) && !("-r".equals(firstParam))) || (!("-n".equals(secondParam)) && !("-r".equals(secondParam)))){
                System.out.println("请以 [-n expCount] -r maxValue 的形式（-n -r无次序要求，必须指定-r参数）输入指令");
                return false;
            }

            switch (firstParam){
                //第一个参数是-n， 则第二个参数比是-r
                case "-n":
                    expStr = command[1];
                    maxStr = command[3];
                    break;
                //同理
                case "-r":
                    maxStr = command[1];
                    expStr = command[3];
                    break;
            }

            try {
                expCounts = Integer.parseInt(expStr);
                maxValue = Integer.parseInt(maxStr);
            }catch (NumberFormatException e){
                System.out.println("-n -r之后的参数应是整数");
                return false;
            }
        }
        return true;
    }

    /**
     * 命令是否包含 -r 参数
     * 是否
     * 且要求必须有-r参数
     * @param command
     * @return
     */
    private boolean hasRParam(String[] command){
        for(String s : command){
            if("-r".equals(s)){
                return true;
            }
        }

        return false;
    }

    public int getExpCounts() {
        return expCounts;
    }

    public int getMaxValue() {
        return maxValue;
    }
}
