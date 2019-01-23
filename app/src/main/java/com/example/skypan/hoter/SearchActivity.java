package com.example.skypan.hoter;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.UUID;

public class SearchActivity extends BaseActivity {
    public static int test=0;
    public static int BluetoothIsOK=0;
    public static int BluetoothOpenOK=0;
    public final static int MESSAGE_DATA = 0x01;
    UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private BroadcastReceiver mRceiver;
    private Button bt;   // 搜索设备按钮
    private ListView listViewDevices;
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> arrayAdapter;
    private BluetoothAdapter bluetoothAdapter;
    public BluetoothDevice bluetoothDevice = null;
    public BluetoothSocket bluetoothSocket = null;
    private OutputStream outputStream = null;
    private InputStream inputStream = null;
    private Location location;
    //接收来自其他线程的消息并处理
//    private MyHandler myHandler = new MyHandler();

    //服务端线程任务
    AcceptThread acceptThread;
//    AcceptThread2 acceptThread2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        //获取本地蓝牙适配器
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter==null){
            Toast.makeText(this,"该设备不支持蓝牙！",Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        initView();
        //检查是否支持蓝牙并打开蓝牙
//        checkBluetoothSupport();
        /**
         * 设置广播接收器
         */
        mRceiver = new BroadcastReceiver() {
            /*监听扫描过程中的变化*/
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                //当设备开始扫描时。
                if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                    //从Intent得到blueDevice对象
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    String dev = device.getName() + ":" + device.getAddress();
                    if (!arrayList.contains(dev)) {
                        arrayList.add(dev);                  //将获取到的蓝牙设备的名称和地址放置到数组ArrayList中去
                    }
                    arrayAdapter.notifyDataSetChanged();    //通知ListView适配器 数据已经发生改变
                }
//                else if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
//                    int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);
//                    switch (state) {
//                        case BluetoothAdapter.STATE_OFF:
//                            //Log.d("aaa", "STATE_OFF 手机蓝牙关闭");
//                            Toast.makeText(SearchActivity.this, "STATE_OFF 手机蓝牙关闭", Toast.LENGTH_SHORT).show();
//                            break;
//                        case BluetoothAdapter.STATE_TURNING_OFF:
//                            //Log.d("aaa", "STATE_TURNING_OFF 手机蓝牙正在关闭");
//                            Toast.makeText(SearchActivity.this, "STATE_TURNING_OFF 手机蓝牙正在关闭", Toast.LENGTH_SHORT).show();
//                            break;
//                        case BluetoothAdapter.STATE_ON:
//                            //Log.d("aaa", "STATE_ON 手机蓝牙开启");
//                            Toast.makeText(SearchActivity.this, "STATE_ON 手机蓝牙开启", Toast.LENGTH_SHORT).show();
//                            break;
//                        case BluetoothAdapter.STATE_TURNING_ON:
//                            //Log.d("aaa", "STATE_TURNING_ON 手机蓝牙正在开启");
//                            Toast.makeText(SearchActivity.this, "TATE_TURNING_ON 手机蓝牙正在开启", Toast.LENGTH_SHORT).show();
//                            break;
//                    }
//                } else if (action.equals(BluetoothDevice.ACTION_BOND_STATE_CHANGED)) {
//                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
//                    String name = device.getName();
//                    Log.d("aaa", "device name: " + name);
//                    int state = intent.getIntExtra(BluetoothDevice.EXTRA_BOND_STATE, -1);
//                    switch (state) {
//                        case BluetoothDevice.BOND_NONE:
//                            Log.d("aaa", "BOND_NONE 删除配对");
//                            Toast.makeText(SearchActivity.this, "BOND_NONE 删除配对", Toast.LENGTH_SHORT).show();
//                            break;
//                        case BluetoothDevice.BOND_BONDING:
//                            Log.d("aaa", "BOND_BONDING 正在配对");
//                            Toast.makeText(SearchActivity.this, "BOND_BONDING 正在配对", Toast.LENGTH_SHORT).show();
//                            break;
//                        case BluetoothDevice.BOND_BONDED:
//                            Log.d("aaa", "BOND_BONDED 配对成功");
//                            Toast.makeText(SearchActivity.this, "BOND_BONDED 配对成功", Toast.LENGTH_SHORT).show();
//                            break;
//                    }
//                } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
////                                Toast.makeText(com.example.SearchActivity.this, "搜索结束!", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                }
////                if (action.equals(BluetoothDevice.ACTION_ACL_DISCONNECTED)){
////                    runOnUiThread(new Runnable() {
////                        @Override
////                        public void run() {
////                            BluetoothIsOK=0;
//////                            Toast.makeText(com.example.SearchActivity.this, "搜索结束!", Toast.LENGTH_SHORT).show();
////                        }
////                    });
////                }
            }
        };
        //注册广播
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothDevice.ACTION_FOUND);
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        intentFilter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);
        registerReceiver(mRceiver, intentFilter);
//        启动接收线程任务
        acceptThread = new AcceptThread();
        acceptThread.start();
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
    /**
     * 初始化布局控件功能
     */
    private void initView() {
        //搜索蓝牙设备,搜索之前断开当前连接,并清空存储蓝牙设备的arrayList
        bt = findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (BluetoothOpenOK==1) {
                    BluetoothIsOK = 0;
                    arrayList.clear();
                    if (bluetoothSocket != null) {
                        try {
                            if (bluetoothSocket.isConnected())
                                bluetoothSocket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (bluetoothAdapter.isDiscovering()) {
                        bluetoothAdapter.cancelDiscovery();
                    }
                    Toast.makeText(SearchActivity.this, "正在搜索蓝牙设备...", Toast.LENGTH_LONG).show();
                    serchBluetoothDevice();
                }else{
                    Toast.makeText(SearchActivity.this,"请先打开蓝牙",Toast.LENGTH_SHORT).show();
                }
            }
        });

        /**
         * 设置ListView点击事件监听,点击item连接相应的蓝牙设备
         */
        listViewDevices = findViewById(R.id.listView_devices);
        arrayList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);//arrayList中存放可用蓝牙设备地址
        listViewDevices.setAdapter(arrayAdapter);    //填充显示扫描出的设备
        listViewDevices.setOnItemClickListener(new AdapterView.OnItemClickListener()  //ListView的监听事件
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, final View view, int i, long l) {
                String devices = arrayAdapter.getItem(i);
                String address = devices.substring(devices.indexOf(":") + 1).trim();
                Toast.makeText(SearchActivity.this, "正在连接,请稍等...", Toast.LENGTH_SHORT).show();

                /**
                 * 连接与断开开启新线程原因:  如果不开新线程,程序在close的时候会闪退
                 */
                //连接新的设备时,断开当前连接
                if (bluetoothSocket != null) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            if (bluetoothSocket.isConnected()) {
                                try {
                                    bluetoothSocket.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }).start();
                }
                //连接新设备
                bluetoothDevice = bluetoothAdapter.getRemoteDevice(address);
//                acceptThread2 = new AcceptThread2();
//                acceptThread2.start();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //获得设备
                            bluetoothSocket = bluetoothDevice.createRfcommSocketToServiceRecord(uuid);
                            bluetoothSocket.connect();
                            outputStream = bluetoothSocket.getOutputStream();
                            inputStream = bluetoothSocket.getInputStream();
                            //在主线程中显示连接成功提示信息
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (bluetoothSocket.isConnected()) {
                                        Toast.makeText(SearchActivity.this, "已连接!", Toast.LENGTH_SHORT).show();
                                        BluetoothIsOK=1;
                                        Intent intent=new Intent();
                                        intent.setClass(SearchActivity.this,MainActivity.class);
                                        startActivity(intent);
                                    }
                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }

   /* private class AcceptThread2 extends Thread {
        public void run() {
            try {
                //获得设备
                bluetoothSocket = bluetoothDevice.createRfcommSocketToServiceRecord(uuid);
                bluetoothSocket.connect();
                outputStream = bluetoothSocket.getOutputStream();
                inputStream = bluetoothSocket.getInputStream();
                //在主线程中显示连接成功提示信息
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (bluetoothSocket.isConnected()) {
                            Toast.makeText(SearchActivity.this, "已连接!", Toast.LENGTH_SHORT).show();
                            BluetoothIsOK=1;
                            Intent intent=new Intent();
                            intent.setClass(SearchActivity.this,MainActivity.class);
                            startActivity(intent);
                        }
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*/
    @Override
    protected void onDestroy() {
        super.onDestroy();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (bluetoothSocket != null)
                        bluetoothSocket.close();

                    if (inputStream != null)
                        inputStream.close();

                    if (outputStream != null)
                        outputStream.close();

                    if (!acceptThread.isInterrupted()) {
                        acceptThread.stopThread();
                    }

//                    if (!acceptThread2.isInterrupted()) {
//                        acceptThread2.stop();
//                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
//        注销广播
        unregisterReceiver(mRceiver);
//        关闭蓝牙
//        bluetoothAdapter.disable();
    }
    /**
     * 处理其他线程发来的消息
     */
//    class MyHandler extends Handler {
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                //该消息附带接收到的数据,用于更新UI显示
//                case 4: {
//                    DisplayMetrics dm = new DisplayMetrics();
//                    getWindowManager().getDefaultDisplay().getMetrics(dm);
//                    if (test==5){
//                        Toast.makeText(SearchActivity.this,"hhhhh"+test,Toast.LENGTH_SHORT).show();
//                    }
//                    break;
//                }
//                default:
//                    break;
//            }
//        }
//    }

    // 服务端接收信息线程。在Java中，如果要想做平行任务处理的话，会在Runnable里面执行你的代码。可以继承Thread类
//    private class AcceptThread extends Thread {
//        private int isStop = 0;
//        private int len=0;
//        public void stopThread() {
//            isStop = 1;
//        }
//        public void run() {
//            while (isStop==0) {
////                try {
////                    Thread.sleep(10);
////                } catch (InterruptedException e) {
////                    e.printStackTrace();
////                }
//                try {
//                    len=inputStream.read();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                /**
//                     * 数据存储部分
//                     */
//                    if (stopdone==0&startdone==1){
//                        String i=Integer.toString(len);
//                        MainActivity.savebreath01(i);
//                    }
//                    /**
//                     * 数据清除部分
//                     */
//                    if (DataAnalysisActivity.cleardone==1){
//                        chuangjianbreath01();
//                    }
//                    Message msg = new Message();
//                    msg.what = 3;
//                    //msg.arg1=len;
//                    myHandler.sendMessage(msg);
//            }
//        }
//    }
    private class AcceptThread extends Thread {
        private Boolean isStop = false;
        private int verify=0;
        private int  value= 0;
        private int temp=0;
        private int temp0=0;
        private int temp00=0;
        private int temp1=0;
        private int temp11=0;
        private int temppassage=0;
        private int[] arrayallvalue=new int[6000000];
        private int[] arrayvalue1=new int[3000];
        private int[] arrayvalue2=new int[3000];
        private int[] arrayvalue3=new int[3000];
        private int[] arrayvalue4=new int[3000];
        private int[] arrayvalue5=new int[3000];
        private int[] arrayvalue6=new int[3000];
        private int[] arrayvalue7=new int[3000];
        private int[] arrayvalue8=new int[3000];
        private int[] fChannel0=new int[3000];
        private int[] fChannel1=new int[3000];
        private int[] fChannel2=new int[3000];
        private int[] fChannel3=new int[3000];
        private int[] fChannel4=new int[3000];
        private int[] fChannel5=new int[3000];
        private int[] fChannel6=new int[3000];
        private int[] fChannel7=new int[3000];
        private int[] fChannel8=new int[3000];
        private int[] fChannel9=new int[3000];
        private int[] fChannel10=new int[3000];
        private int[] fChannel11=new int[3000];
        public void stopThread() {
            isStop = true;
        }
        public void run() {
            while (!isStop) {
                while (inputStream != null) {
                    try {
                        //按下保存数据按钮后
                        if (MainActivity.stopdone == 0 & MainActivity.startdone == 1) {
                            //读取输入流中的数据
                            value = inputStream.read();
                            arrayallvalue[temp0]=value;
                            temp=(temp0+temp00*3000)-(temp1+temp11*3000);
                            if (temp==29){
                                //检测到帧头
                                if (arrayallvalue[temp1]==165&arrayallvalue[(temp1+1)%3000]==165&arrayallvalue[(temp1+2)%3000]==1&arrayallvalue[(temp1+3)%3000]==24){
                                    //速度问题没有考虑！！
                                    for (int i=4;i<28;i++){
                                        verify+=arrayallvalue[(temp1+i)%3000];
                                    }
                                    if ((arrayallvalue[(temp1+29)%3000]*256+arrayallvalue[(temp1+28)%3000])==verify){
                                        //清除校验位
                                        verify=0;
                                        //判断正负
                                        //第一个通道
                                        if (arrayallvalue[(temp1+4)%3000]<128){
                                            arrayvalue1[temppassage]=arrayallvalue[(temp1+4)%3000]*65536+arrayallvalue[(temp1+5)%3000]*256+arrayallvalue[(temp1+6)%3000];
                                        }else{
                                            arrayvalue1[temppassage]=-((255-arrayallvalue[(temp1+4)%3000])*65536+(255-arrayallvalue[(temp1+5)%3000])*256+(256-arrayallvalue[(temp1+6)%3000]));
                                        }
                                        //第二个通道
                                        if (arrayallvalue[(temp1+7)%3000]<128){
                                            arrayvalue2[temppassage]=arrayallvalue[(temp1+7)%3000]*65536+arrayallvalue[(temp1+8)%3000]*256+arrayallvalue[(temp1+9)%3000];
                                        }else{
                                            arrayvalue2[temppassage]=-((255-arrayallvalue[(temp1+7)%3000])*65536+(255-arrayallvalue[(temp1+8)%3000])*256+(256-arrayallvalue[(temp1+9)%3000]));
                                        }
                                        //第三个通道
                                        if (arrayallvalue[(temp1+10)%3000]<128){
                                            arrayvalue3[temppassage]=arrayallvalue[(temp1+10)%3000]*65536+arrayallvalue[(temp1+11)%3000]*256+arrayallvalue[(temp1+12)%3000];
                                        }else{
                                            arrayvalue3[temppassage]=-((255-arrayallvalue[(temp1+10)%3000])*65536+(255-arrayallvalue[(temp1+11)%3000])*256+(256-arrayallvalue[(temp1+12)%3000]));
                                        }
                                        //第四个通道
                                        if (arrayallvalue[(temp1+13)%3000]<128){
                                            arrayvalue4[temppassage]=arrayallvalue[(temp1+13)%3000]*65536+arrayallvalue[(temp1+14)%3000]*256+arrayallvalue[(temp1+15)%3000];
                                        }else{
                                            arrayvalue4[temppassage]=-((255-arrayallvalue[(temp1+13)%3000])*65536+(255-arrayallvalue[(temp1+14)%3000])*256+(256-arrayallvalue[(temp1+15)%3000]));
                                        }
                                        //第五个通道
                                        if (arrayallvalue[(temp1+16)%3000]<128){
                                            arrayvalue5[temppassage]=arrayallvalue[(temp1+16)%3000]*65536+arrayallvalue[(temp1+17)%3000]*256+arrayallvalue[(temp1+18)%3000];
                                        }else{
                                            arrayvalue5[temppassage]=-((255-arrayallvalue[(temp1+16)%3000])*65536+(255-arrayallvalue[(temp1+17)%3000])*256+(256-arrayallvalue[(temp1+18)%3000]));
                                        }
                                        //第六个通道
                                        if (arrayallvalue[(temp1+19)%3000]<128){
                                            arrayvalue6[temppassage]=arrayallvalue[(temp1+19)%3000]*65536+arrayallvalue[(temp1+20)%3000]*256+arrayallvalue[(temp1+21)%3000];
                                        }else{
                                            arrayvalue6[temppassage]=-((255-arrayallvalue[(temp1+19)%3000])*65536+(255-arrayallvalue[(temp1+20)%3000])*256+(256-arrayallvalue[(temp1+21)%3000]));
                                        }
                                        //第七个通道
                                        if (arrayallvalue[(temp1+22)%3000]<128){
                                            arrayvalue7[temppassage]=arrayallvalue[(temp1+22)%3000]*65536+arrayallvalue[(temp1+23)%3000]*256+arrayallvalue[(temp1+24)%3000];
                                        }else{
                                            arrayvalue7[temppassage]=-((255-arrayallvalue[(temp1+22)%3000])*65536+(255-arrayallvalue[(temp1+23)%3000])*256+(256-arrayallvalue[(temp1+24)%3000]));
                                        }
                                        //第八个通道
                                        if (arrayallvalue[(temp1+25)%3000]<128){
                                            arrayvalue8[temppassage]=arrayallvalue[(temp1+25)%3000]*65536+arrayallvalue[(temp1+26)%3000]*256+arrayallvalue[(temp1+27)%3000];
                                        }else{
                                            arrayvalue8[temppassage]=-((255-arrayallvalue[(temp1+25)%3000])*65536+(255-arrayallvalue[(temp1+26)%3000])*256+(256-arrayallvalue[(temp1+27)%3000]));
                                        }
                                        //将八通道保存成12导联
                                        fChannel0[temppassage]=arrayvalue8[temppassage];                              //V1
                                        fChannel1[temppassage]=arrayvalue7[temppassage];                              //V2
                                        fChannel2[temppassage]=arrayvalue6[temppassage];                              //V3
                                        fChannel3[temppassage]=arrayvalue5[temppassage];                              //V4
                                        fChannel4[temppassage]=arrayvalue4[temppassage];                              //V5
                                        fChannel5[temppassage]=arrayvalue1[temppassage];                              //V6
                                        fChannel6[temppassage]=arrayvalue2[temppassage];                              //I
                                        fChannel7[temppassage]=arrayvalue3[temppassage];                              //II
                                        fChannel8[temppassage]=arrayvalue3[temppassage]-arrayvalue2[temppassage];     //III
                                        fChannel9[temppassage]=-(fChannel6[temppassage]+fChannel7[temppassage])/2;  //AVR
                                        fChannel10[temppassage]=fChannel6[temppassage]-fChannel7[temppassage]/2;    //AVL
                                        fChannel11[temppassage]=fChannel7[temppassage]-fChannel6[temppassage]/2;    //AVF
                                        /**
                                         * 数据存储部分
                                         */
                                        String j0 = Integer.toString(fChannel0[temppassage]);
                                        MainActivity.saveall(j0);
                                        String j1 = Integer.toString(fChannel1[temppassage]);
                                        MainActivity.saveall(j1);
                                        String j2 = Integer.toString(fChannel2[temppassage]);
                                        MainActivity.saveall(j2);
                                        String j3 = Integer.toString(fChannel3[temppassage]);
                                        MainActivity.saveall(j3);
                                        String j4 = Integer.toString(fChannel4[temppassage]);
                                        MainActivity.saveall(j4);
                                        String j5 = Integer.toString(fChannel5[temppassage]);
                                        MainActivity.saveall(j5);
                                        String j6 = Integer.toString(fChannel6[temppassage]);
                                        MainActivity.saveall(j6);
                                        String j7 = Integer.toString(fChannel7[temppassage]);
                                        MainActivity.saveall(j7);
                                        String j8 = Integer.toString(fChannel8[temppassage]);
                                        MainActivity.saveall(j8);
                                        String j9 = Integer.toString(fChannel9[temppassage]);
                                        MainActivity.saveall(j9);
                                        String j10 = Integer.toString(fChannel10[temppassage]);
                                        MainActivity.saveall(j10);
                                        String j11 = Integer.toString(fChannel11[temppassage]);
                                        MainActivity.saveall(j11);
//                                        String i0 = Integer.toString(fChannel0[temppassage]);
//                                        MainActivity.savefChannel0(i0);
//                                        String i1 = Integer.toString(fChannel1[temppassage]);
//                                        MainActivity.savefChannel1(i1);
//                                        String i2 = Integer.toString(fChannel2[temppassage]);
//                                        MainActivity.savefChannel2(i2);
//                                        String i3 = Integer.toString(fChannel3[temppassage]);
//                                        MainActivity.savefChannel3(i3);
//                                        String i4 = Integer.toString(fChannel4[temppassage]);
//                                        MainActivity.savefChannel4(i4);
//                                        String i5 = Integer.toString(fChannel5[temppassage]);
//                                        MainActivity.savefChannel5(i5);
//                                        String i6 = Integer.toString(fChannel6[temppassage]);
//                                        MainActivity.savefChannel6(i6);
//                                        String i7 = Integer.toString(fChannel7[temppassage]);
//                                        MainActivity.savefChannel7(i7);
//                                        String i8 = Integer.toString(fChannel8[temppassage]);
//                                        MainActivity.savefChannel8(i8);
//                                        String i9 = Integer.toString(fChannel9[temppassage]);
//                                        MainActivity.savefChannel9(i9);
//                                        String i10 = Integer.toString(fChannel10[temppassage]);
//                                        MainActivity.savefChannel10(i10);
//                                        String i11 = Integer.toString(fChannel11[temppassage]);
//                                        MainActivity.savefChannel11(i11);
                                        temppassage++;
                                    }
                                }
                                //保证以30个数据的长度，一个一个数据右移
                                temp0++;
                                temp1++;
                            }else{
                                temp0++;
                            }
                            if (temp0==3000){
                                temp0=0;
                                temp00=1;
                            }
                            if (temp1==3000){
                                temp1=0;
                                temp11=1;
                            }
                            if (temp00==1&temp11==1){
                                temp00=0;
                                temp11=0;
                            }
                            if (temppassage==3000){
                                temppassage=0;
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
//                if (bluetoothAdapter.STATE_DISCONNECTED==1){
//                    BluetoothIsOK=0;
//                }
                //闪退
//                if (!bluetoothSocket.isConnected()){
//                    BluetoothIsOK=0;
//                }
            }
        }
    }
    /**
     * 搜索蓝牙设备
     * startDiscovery方法是异步的
     */
    private void serchBluetoothDevice() {
        //搜索设备
        bluetoothAdapter.startDiscovery();
        while (!bluetoothAdapter.startDiscovery()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void checkBluetoothSupport() {
        //检测是否支持蓝牙
        if (bluetoothAdapter != null) {
            //打开蓝牙
            if (!bluetoothAdapter.isEnabled()) {
                Intent enableIntent=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableIntent,2);
//                对话框提示
//                startActivity(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE));
//                静默打开
//                bluetoothAdapter.enable();
//                bluetoothAdapter.cancelDiscovery();
                BluetoothOpenOK=1;
            }
        } else {
            finish();
        }
    }
   /* @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        switch (requestCode){
            case 2:
                if(resultCode==Activity.RESULT_OK){
                    //同意请求蓝牙
                }
                break;
        }
    }*/
}
