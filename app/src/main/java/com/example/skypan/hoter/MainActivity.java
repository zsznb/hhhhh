package com.example.skypan.hoter;

import android.Manifest;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.example.skypan.hoter.SearchActivity.BluetoothIsOK;

public class MainActivity extends BaseActivity {
    private static ImageView mIv;
    private TextView mtv;
    public static int time=0,startdone=0,stopdone=0,cleardone=0;
    private MyHandler1 myHandler1 = new MyHandler1();
    AcceptThread1 acceptThread1;
    AcceptThread2 acceptThread2;
    static FileOutputStream fileOutputStream=null;
    static FileOutputStream fileOutputStream00=null;
    static FileOutputStream fileOutputStream01=null;
    static FileOutputStream fileOutputStream02=null;
    static FileOutputStream fileOutputStream03=null;
    static FileOutputStream fileOutputStream04=null;
    static FileOutputStream fileOutputStream05=null;
    static FileOutputStream fileOutputStream06=null;
    static FileOutputStream fileOutputStream07=null;
    static FileOutputStream fileOutputStream08=null;
    static FileOutputStream fileOutputStream09=null;
    static FileOutputStream fileOutputStream10=null;
    static FileOutputStream fileOutputStream11=null;
    private Button btn;
    private static Boolean STOP=false;
    private static int count=0;
    private long mEixtTime=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        去掉顶部标题栏
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        mIv=findViewById(R.id.iv_connect);
        btn=findViewById(R.id.button_search);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(MainActivity.this,SearchActivity.class);
                startActivity(intent);
            }
        });
        mtv=findViewById(R.id.tv_time);
        mtv.setText(time+"秒");
        //请求存储权限
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
//        chuangjian();
        chuangjianall();
        acceptThread2=new AcceptThread2();
        acceptThread2.start();
    }
    /**
     * 创建文件夹及文件
     */
    private void chuangjianall(){
        try {
            //创建文件夹Hoter
            File dir=new File(Environment.getExternalStorageDirectory(),"Hoter");
            if (!dir.exists()){
                dir.mkdirs();
            }
            //创建文件passage1.txt
            File file=new File(dir,"data.txt");
            if (!file.exists()){
                file.createNewFile();
            }
            fileOutputStream=new FileOutputStream(file);
            String V1="V1";
            fileOutputStream.write(V1.getBytes());
            String Tab="\t";
            fileOutputStream.write(Tab.getBytes());
            String V2="V2";
            fileOutputStream.write(V2.getBytes());
            fileOutputStream.write(Tab.getBytes());
            String V3="V3";
            fileOutputStream.write(V3.getBytes());
            fileOutputStream.write(Tab.getBytes());
            String V4="V4";
            fileOutputStream.write(V4.getBytes());
            fileOutputStream.write(Tab.getBytes());
            String V5="V5";
            fileOutputStream.write(V5.getBytes());
            fileOutputStream.write(Tab.getBytes());
            String V6="V6";
            fileOutputStream.write(V6.getBytes());
            fileOutputStream.write(Tab.getBytes());
            String I="I";
            fileOutputStream.write(I.getBytes());
            fileOutputStream.write(Tab.getBytes());
            String II="II";
            fileOutputStream.write(II.getBytes());
            fileOutputStream.write(Tab.getBytes());
            String III="III";
            fileOutputStream.write(III.getBytes());
            fileOutputStream.write(Tab.getBytes());
            String AVR="AVR";
            fileOutputStream.write(AVR.getBytes());
            fileOutputStream.write(Tab.getBytes());
            String AVL="AVL";
            fileOutputStream.write(AVL.getBytes());
            fileOutputStream.write(Tab.getBytes());
            String AVF="AVF";
            fileOutputStream.write(AVF.getBytes());
            fileOutputStream.write(Tab.getBytes());
            String newLine = System.getProperty("line.separator");
            fileOutputStream.write(newLine.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void chuangjian(){
        chuangjianfChannel0();
        chuangjianfChannel1();
        chuangjianfChannel2();
        chuangjianfChannel3();
        chuangjianfChannel4();
        chuangjianfChannel5();
        chuangjianfChannel6();
        chuangjianfChannel7();
        chuangjianfChannel8();
        chuangjianfChannel9();
        chuangjianfChannel10();
        chuangjianfChannel11();
    }
    private void chuangjianfChannel0(){
        try {
            //创建文件夹Hoter
            File dir01=new File(Environment.getExternalStorageDirectory(),"Hoter");
            if (!dir01.exists()){
                dir01.mkdirs();
            }
            //创建文件passage1.txt
            File file01=new File(dir01,"V1.txt");
            if (!file01.exists()){
                file01.createNewFile();
            }
            fileOutputStream00=new FileOutputStream(file01);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void chuangjianfChannel1(){
        try {
            //创建文件夹Hoter
            File dir01=new File(Environment.getExternalStorageDirectory(),"Hoter");
            if (!dir01.exists()){
                dir01.mkdirs();
            }
            //创建文件passage1.txt
            File file01=new File(dir01,"V2.txt");
            if (!file01.exists()){
                file01.createNewFile();
            }
            fileOutputStream01=new FileOutputStream(file01);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void chuangjianfChannel2(){
        try {
            //创建文件夹Hoter
            File dir01=new File(Environment.getExternalStorageDirectory(),"Hoter");
            if (!dir01.exists()){
                dir01.mkdirs();
            }
            //创建文件passage1.txt
            File file01=new File(dir01,"V3.txt");
            if (!file01.exists()){
                file01.createNewFile();
            }
            fileOutputStream02=new FileOutputStream(file01);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void chuangjianfChannel3(){
        try {
            //创建文件夹Hoter
            File dir01=new File(Environment.getExternalStorageDirectory(),"Hoter");
            if (!dir01.exists()){
                dir01.mkdirs();
            }
            //创建文件passage1.txt
            File file01=new File(dir01,"V4.txt");
            if (!file01.exists()){
                file01.createNewFile();
            }
            fileOutputStream03=new FileOutputStream(file01);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void chuangjianfChannel4(){
        try {
            //创建文件夹Hoter
            File dir01=new File(Environment.getExternalStorageDirectory(),"Hoter");
            if (!dir01.exists()){
                dir01.mkdirs();
            }
            //创建文件passage1.txt
            File file01=new File(dir01,"V5.txt");
            if (!file01.exists()){
                file01.createNewFile();
            }
            fileOutputStream04=new FileOutputStream(file01);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void chuangjianfChannel5(){
        try {
            //创建文件夹Hoter
            File dir01=new File(Environment.getExternalStorageDirectory(),"Hoter");
            if (!dir01.exists()){
                dir01.mkdirs();
            }
            //创建文件passage1.txt
            File file01=new File(dir01,"V6.txt");
            if (!file01.exists()){
                file01.createNewFile();
            }
            fileOutputStream05=new FileOutputStream(file01);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void chuangjianfChannel6(){
        try {
            //创建文件夹Hoter
            File dir01=new File(Environment.getExternalStorageDirectory(),"Hoter");
            if (!dir01.exists()){
                dir01.mkdirs();
            }
            //创建文件passage1.txt
            File file01=new File(dir01,"I.txt");
            if (!file01.exists()){
                file01.createNewFile();
            }
            fileOutputStream06=new FileOutputStream(file01);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void chuangjianfChannel7(){
        try {
            //创建文件夹Hoter
            File dir01=new File(Environment.getExternalStorageDirectory(),"Hoter");
            if (!dir01.exists()){
                dir01.mkdirs();
            }
            //创建文件passage1.txt
            File file01=new File(dir01,"II.txt");
            if (!file01.exists()){
                file01.createNewFile();
            }
            fileOutputStream07=new FileOutputStream(file01);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void chuangjianfChannel8(){
        try {
            //创建文件夹Hoter
            File dir01=new File(Environment.getExternalStorageDirectory(),"Hoter");
            if (!dir01.exists()){
                dir01.mkdirs();
            }
            //创建文件passage1.txt
            File file01=new File(dir01,"III.txt");
            if (!file01.exists()){
                file01.createNewFile();
            }
            fileOutputStream08=new FileOutputStream(file01);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void chuangjianfChannel9(){
        try {
            //创建文件夹Hoter
            File dir01=new File(Environment.getExternalStorageDirectory(),"Hoter");
            if (!dir01.exists()){
                dir01.mkdirs();
            }
            //创建文件passage1.txt
            File file01=new File(dir01,"AVR.txt");
            if (!file01.exists()){
                file01.createNewFile();
            }
            fileOutputStream09=new FileOutputStream(file01);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void chuangjianfChannel10(){
        try {
            //创建文件夹Hoter
            File dir01=new File(Environment.getExternalStorageDirectory(),"Hoter");
            if (!dir01.exists()){
                dir01.mkdirs();
            }
            //创建文件passage1.txt
            File file01=new File(dir01,"AVL.txt");
            if (!file01.exists()){
                file01.createNewFile();
            }
            fileOutputStream10=new FileOutputStream(file01);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void chuangjianfChannel11(){
        try {
            //创建文件夹Hoter
            File dir01=new File(Environment.getExternalStorageDirectory(),"Hoter");
            if (!dir01.exists()){
                dir01.mkdirs();
            }
            //创建文件passage1.txt
            File file01=new File(dir01,"AVF.txt");
            if (!file01.exists()){
                file01.createNewFile();
            }
            fileOutputStream11=new FileOutputStream(file01);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 在主线程中更改UI
     */
    class MyHandler1 extends Handler {
        @Override
        public void handleMessage(Message msg){
//            Toast.makeText(MainActivity.this,"BluetoothIsOK"+BluetoothIsOK,Toast.LENGTH_SHORT).show();
            switch (msg.what){
                case 1:{
                    mtv.setText(time+"秒");
                    break;
                }
                case 2:{
                    mtv.setText(time+"秒");
                    break;
                }
                case 3:{
                    break;
                }
            }
        }
    }
    private class AcceptThread2 extends Thread{
        private int j=1;
        public void run(){
            while(j==1) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (BluetoothIsOK==1){
                    mIv.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.mipmap.connected));
                }else{
                    mIv.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.mipmap.disconnected));
                }
            }
        }
    }
    /**
     * 开启线程进行计时部分程序的执行
     */
    private class AcceptThread1 extends Thread {
        public void stopThread1() {
            STOP = true;
        }
        public void run() {
            while(STOP==false){
                if (startdone==1&stopdone==0){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (stopdone==0) {
                        time++;
                        Message msg = new Message();
                        msg.what= 1;
                        myHandler1.sendMessage(msg);
                    }
                }
                if (cleardone==1){
                    time=0;
                    Message msg=new Message();
                    msg.what=2;
                    myHandler1.sendMessage(msg);
                    chuangjianfChannel0();
                    chuangjianfChannel1();
                    chuangjianfChannel2();
                    chuangjianfChannel3();
                    chuangjianfChannel4();
                    chuangjianfChannel5();
                    chuangjianfChannel6();
                    chuangjianfChannel7();
                    chuangjianfChannel8();
                    chuangjianfChannel9();
                    chuangjianfChannel10();
                    chuangjianfChannel11();
                    cleardone=0;
                }
            }
        }
    }
    /**
     * 保存数据
     * @param content
     */
    public static void saveall(String content){
        count++;
        if (stopdone==0&startdone==1){
            try {
                fileOutputStream.write(content.getBytes());
                //加Tab
                String Tab="\t";
                fileOutputStream.write(Tab.getBytes());
                if (count==12) {
                    //换行
                    String newLine = System.getProperty("line.separator");
                    fileOutputStream.write(newLine.getBytes());
                    count=0;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void savefChannel0(String content){
        if (stopdone==0&startdone==1){
            try {
                fileOutputStream00.write(content.getBytes());
                //换行
                String newLine = System.getProperty("line.separator");
                fileOutputStream00.write(newLine.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void savefChannel1(String content){
        if (stopdone==0&startdone==1){
            try {
                fileOutputStream01.write(content.getBytes());
                //换行
                String newLine = System.getProperty("line.separator");
                fileOutputStream01.write(newLine.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void savefChannel2(String content){
        if (stopdone==0&startdone==1){
            try {
                fileOutputStream02.write(content.getBytes());
                //换行
                String newLine = System.getProperty("line.separator");
                fileOutputStream02.write(newLine.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void savefChannel3(String content){
        if (stopdone==0&startdone==1){
            try {
                fileOutputStream03.write(content.getBytes());
                //换行
                String newLine = System.getProperty("line.separator");
                fileOutputStream03.write(newLine.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void savefChannel4(String content){
        if (stopdone==0&startdone==1){
            try {
                fileOutputStream04.write(content.getBytes());
                //换行
                String newLine = System.getProperty("line.separator");
                fileOutputStream04.write(newLine.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void savefChannel5(String content){
        if (stopdone==0&startdone==1){
            try {
                fileOutputStream05.write(content.getBytes());
                //换行
                String newLine = System.getProperty("line.separator");
                fileOutputStream05.write(newLine.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void savefChannel6(String content){
        if (stopdone==0&startdone==1){
            try {
                fileOutputStream06.write(content.getBytes());
                //换行
                String newLine = System.getProperty("line.separator");
                fileOutputStream06.write(newLine.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void savefChannel7(String content){
        if (stopdone==0&startdone==1){
            try {
                fileOutputStream07.write(content.getBytes());
                //换行
                String newLine = System.getProperty("line.separator");
                fileOutputStream07.write(newLine.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void savefChannel8(String content){
        if (stopdone==0&startdone==1){
            try {
                fileOutputStream08.write(content.getBytes());
                //换行
                String newLine = System.getProperty("line.separator");
                fileOutputStream08.write(newLine.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void savefChannel9(String content){
        if (stopdone==0&startdone==1){
            try {
                fileOutputStream09.write(content.getBytes());
                //换行
                String newLine = System.getProperty("line.separator");
                fileOutputStream09.write(newLine.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void savefChannel10(String content){
        if (stopdone==0&startdone==1){
            try {
                fileOutputStream10.write(content.getBytes());
                //换行
                String newLine = System.getProperty("line.separator");
                fileOutputStream10.write(newLine.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void savefChannel11(String content){
        if (stopdone==0&startdone==1){
            try {
                fileOutputStream11.write(content.getBytes());
                //换行
                String newLine = System.getProperty("line.separator");
                fileOutputStream11.write(newLine.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 开始存储按钮
     * @param view
     */
    public void start(View view){
        if (BluetoothIsOK==1) {
        startdone = 1;
        stopdone = 0;
        cleardone = 0;
        STOP=false;
        //启动接收线程任务
        acceptThread1 = new AcceptThread1();
        acceptThread1.start();
        }else{
            Toast.makeText(this,"请先连接蓝牙",Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * 停止存储按钮
     * @param view
     */
    public void stop(View view){
        if (BluetoothIsOK==1) {
        stopdone = 1;
        startdone = 0;
        cleardone = 0;
        acceptThread1.stopThread1();
        }else{
            Toast.makeText(this,"请先连接蓝牙",Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * 清除存储按钮
     * @param view
     */
    public void clear(View view){
        if (BluetoothIsOK==1) {
        cleardone=1;
        startdone=0;
        stopdone=0;
        time=0;
        mtv.setText(time+"秒");
        chuangjianfChannel0();
        chuangjianfChannel1();
        chuangjianfChannel2();
        chuangjianfChannel3();
        chuangjianfChannel4();
        chuangjianfChannel5();
        chuangjianfChannel6();
        chuangjianfChannel7();
        chuangjianfChannel8();
        chuangjianfChannel9();
        chuangjianfChannel10();
        chuangjianfChannel11();
        acceptThread1.stopThread1();
        }else{
            Toast.makeText(this,"请先连接蓝牙",Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * 销毁活动
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!acceptThread1.isInterrupted()) {
            acceptThread1.stopThread1();
        }
        if (!acceptThread2.isInterrupted()) {
            acceptThread2.j=0;
        }
//        if (fileOutputStream00!=null|fileOutputStream01!=null|fileOutputStream02!=null
//                |fileOutputStream03!=null|fileOutputStream04!=null|fileOutputStream05!=null
//                |fileOutputStream06!=null|fileOutputStream07!=null|fileOutputStream08!=null
//                |fileOutputStream09!=null|fileOutputStream10!=null|fileOutputStream11!=null) {
//            try {
//                fileOutputStream00.close();
//                fileOutputStream01.close();
//                fileOutputStream02.close();
//                fileOutputStream03.close();
//                fileOutputStream04.close();
//                fileOutputStream05.close();
//                fileOutputStream06.close();
//                fileOutputStream07.close();
//                fileOutputStream08.close();
//                fileOutputStream09.close();
//                fileOutputStream10.close();
//                fileOutputStream11.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        if (fileOutputStream00!=null){
            try {
                fileOutputStream00.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (fileOutputStream01!=null){
            try {
                fileOutputStream01.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (fileOutputStream02!=null){
            try {
                fileOutputStream02.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (fileOutputStream03!=null){
            try {
                fileOutputStream03.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (fileOutputStream04!=null){
            try {
                fileOutputStream04.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (fileOutputStream05!=null){
            try {
                fileOutputStream05.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (fileOutputStream06!=null){
            try {
                fileOutputStream06.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (fileOutputStream07!=null){
            try {
                fileOutputStream07.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (fileOutputStream08!=null){
            try {
                fileOutputStream08.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (fileOutputStream09!=null){
            try {
                fileOutputStream09.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (fileOutputStream10!=null){
            try {
                fileOutputStream10.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (fileOutputStream11!=null){
            try {
                fileOutputStream11.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if (keyCode==KeyEvent.KEYCODE_BACK){
            exit();
            return false;
        }
        return super.onKeyDown(keyCode,event);
    }
    public void exit(){
        if ((System.currentTimeMillis()-mEixtTime)>2000){
            Toast.makeText(getApplicationContext(),"再按一次退出程序",Toast.LENGTH_SHORT).show();
            mEixtTime=System.currentTimeMillis();
        }else{
            AppExit(this);
        }
    }
    public void AppExit(Context context) {
        try {
            ActivityCollector.finishAll();
            ActivityManager activityMgr = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.killBackgroundProcesses(context.getPackageName());
            System.exit(0);
        } catch (Exception ignored) {}
    }
}
