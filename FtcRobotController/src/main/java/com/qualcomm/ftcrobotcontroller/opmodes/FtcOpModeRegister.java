

package com.qualcomm.ftcrobotcontroller.opmodes;

        import com.qualcomm.ftcrobotcontroller.Krakens.Drive;
        import com.qualcomm.ftcrobotcontroller.Krakens.KrakenAutoBMtn1;
        import com.qualcomm.ftcrobotcontroller.Krakens.KrakenAutoBMtn2;
        import com.qualcomm.ftcrobotcontroller.Krakens.KrakenAutoBlue;
        import com.qualcomm.ftcrobotcontroller.Krakens.KrakenAutoBlue10;
        import com.qualcomm.ftcrobotcontroller.Krakens.KrakenAutoBlue10Climbers;
        import com.qualcomm.ftcrobotcontroller.Krakens.KrakenAutoBlue5;
        import com.qualcomm.ftcrobotcontroller.Krakens.KrakenAutoBlue5Climbers;
        import com.qualcomm.ftcrobotcontroller.Krakens.KrakenAutoBlueClimbers;
        import com.qualcomm.ftcrobotcontroller.Krakens.KrakenAutoRMtn1;
        import com.qualcomm.ftcrobotcontroller.Krakens.KrakenAutoRMtn2;
        import com.qualcomm.ftcrobotcontroller.Krakens.KrakenAutoRMtn3;
        import com.qualcomm.ftcrobotcontroller.Krakens.KrakenAutoRMtn4;
        import com.qualcomm.ftcrobotcontroller.Krakens.KrakenAutoRed10;
        import com.qualcomm.ftcrobotcontroller.Krakens.KrakenAutoRed10Climbers;
        import com.qualcomm.ftcrobotcontroller.Krakens.KrakenAutoRed5;
        import com.qualcomm.ftcrobotcontroller.Krakens.KrakenAutoRed;
        import com.qualcomm.ftcrobotcontroller.Krakens.KrakenAutoRed5Climbers;
        import com.qualcomm.ftcrobotcontroller.Krakens.KrakenAutoRedArmTest;
        import com.qualcomm.ftcrobotcontroller.Krakens.KrakenAutoRedClimbers;
        import com.qualcomm.ftcrobotcontroller.Krakens.SafeDrive;
        import com.qualcomm.robotcore.eventloop.opmode.OpModeManager;
        import com.qualcomm.robotcore.eventloop.opmode.OpModeRegister;

public class FtcOpModeRegister implements OpModeRegister {

    public void register(OpModeManager manager) {
        //manager.register("Safe Drive!", SafeDrive.class);//dangerous
        manager.register("Simple_drive!", Drive.class);

        int redOrBlue = 0; //BLUE = 0, RED = 1
        switch(redOrBlue) {
            case 0:
                manager.register("AutoBlue!", KrakenAutoBlue.class);
                manager.register("AutoBlue! Climbers", KrakenAutoBlueClimbers.class);
                manager.register("AutoBlue! 5 sec", KrakenAutoBlue5.class);
                manager.register("AutoBlue! 5 sec Climbers", KrakenAutoBlue5Climbers.class);
                manager.register("AutoBlue! 10 sec", KrakenAutoBlue10.class);
                manager.register("AutoBlue! 10 sec Climbers", KrakenAutoBlue10Climbers.class);
                manager.register("AutoBMtn1", KrakenAutoBMtn1.class);
                manager.register("AutoBMtn2", KrakenAutoBMtn2.class);
                break;
            case 1:

                manager.register("AutoRed!", KrakenAutoRed.class);
                manager.register("AutoRed Climbers", KrakenAutoRedClimbers.class);
                manager.register("AutoRed! 5 sec", KrakenAutoRed5.class);
                manager.register("AutoRed 5 sec climbers", KrakenAutoRed5Climbers.class);
                manager.register("AutoRed! 10 sec", KrakenAutoRed10.class);
                manager.register("AutoRed 10 sec climbers", KrakenAutoRed10Climbers.class);
                manager.register("AutoRMtn1", KrakenAutoRMtn1.class);
                manager.register("AutoRMtn2", KrakenAutoRMtn2.class);
                manager.register("AutoRMtn3", KrakenAutoRMtn3.class);
                manager.register("AutoRMtn4", KrakenAutoRMtn4.class);
                break;
        }

    }
}