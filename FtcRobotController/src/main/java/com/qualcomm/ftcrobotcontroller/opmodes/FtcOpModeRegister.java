

package com.qualcomm.ftcrobotcontroller.opmodes;

        import com.qualcomm.ftcrobotcontroller.Krakens.Drive;
        import com.qualcomm.ftcrobotcontroller.Krakens.KrakenAutoArmTest;
        import com.qualcomm.ftcrobotcontroller.Krakens.KrakenAutoBMtn1;
        import com.qualcomm.ftcrobotcontroller.Krakens.KrakenAutoBMtn2;
        import com.qualcomm.ftcrobotcontroller.Krakens.KrakenAutoBlue10Climbers;
        import com.qualcomm.ftcrobotcontroller.Krakens.KrakenAutoBlue15Climbers;
        import com.qualcomm.ftcrobotcontroller.Krakens.KrakenAutoBlue5Climbers;
        import com.qualcomm.ftcrobotcontroller.Krakens.KrakenAutoRMtn1;
        import com.qualcomm.ftcrobotcontroller.Krakens.KrakenAutoRMtn2;
        import com.qualcomm.ftcrobotcontroller.Krakens.KrakenAutoRed10;
        import com.qualcomm.ftcrobotcontroller.Krakens.KrakenAutoRed10Climbers;
        import com.qualcomm.ftcrobotcontroller.Krakens.KrakenAutoRed15;
        import com.qualcomm.ftcrobotcontroller.Krakens.KrakenAutoRed15Climbers;
        import com.qualcomm.ftcrobotcontroller.Krakens.KrakenAutoRed5;
        import com.qualcomm.ftcrobotcontroller.Krakens.KrakenAutoBlue;
        import com.qualcomm.ftcrobotcontroller.Krakens.KrakenAutoRed;
        import com.qualcomm.ftcrobotcontroller.Krakens.KrakenAutoBlue10;
        import com.qualcomm.ftcrobotcontroller.Krakens.KrakenAutoBlue15;
        import com.qualcomm.ftcrobotcontroller.Krakens.KrakenAutoBlue5;
        import com.qualcomm.ftcrobotcontroller.Krakens.KrakenAutoRedArmTest;
        import com.qualcomm.ftcrobotcontroller.Krakens.KrakenAutoRedClimbers;
        import com.qualcomm.robotcore.eventloop.opmode.OpModeManager;
        import com.qualcomm.robotcore.eventloop.opmode.OpModeRegister;

public class FtcOpModeRegister implements OpModeRegister {

    public void register(OpModeManager manager) {
        manager.register("Drive!", Drive.class);


       /* manager.register("AutoBlue!", KrakenAutoBlue.class);
        manager.register("AutoBlue! Climbers", KrakenAutoRedArmTest.class);
        manager.register("AutoBlue! 5 sec", KrakenAutoBlue5.class);
        manager.register("AutoBlue! 5 sec Climbers", KrakenAutoBlue5Climbers.class);
        manager.register("AutoBlue! 10 sec", KrakenAutoBlue10.class);
        manager.register("AutoBlue! 10 sec Climbers", KrakenAutoBlue10Climbers.class);
        manager.register("AutoBlue! 15 sec", KrakenAutoBlue15.class);
        manager.register("AutoBlue! 15 sec Climbers", KrakenAutoBlue15Climbers.class);
        manager.register("AutoBMtn1", KrakenAutoBMtn1.class);
        manager.register("AutoBMtn2", KrakenAutoBMtn2.class); */
        manager.register("AutoRed!", KrakenAutoRed.class);
        manager.register("AutoRed Climbers", KrakenAutoRedClimbers.class);
        manager.register("AutoRed! 5 sec", KrakenAutoRed5.class);
        manager.register("AutoRed 5 sec", KrakenAutoRed5.class);
        manager.register("AutoRed! 10 sec", KrakenAutoRed10.class);
        manager.register("AutoRed 10 sec climbers", KrakenAutoRed10Climbers.class);
        manager.register("AutoRed! 15 sec", KrakenAutoRed15.class);
        manager.register("AutoRed 15 sec Climbers", KrakenAutoRed15Climbers.class);
        manager.register("AutoRMtn1", KrakenAutoRMtn1.class);
        manager.register("AutoRMtn2", KrakenAutoRMtn2.class);

    }
}