package com.qualcomm.ftcrobotcontroller.Krakens;

public class SafeDrive extends KrakenTelementry
{
        public SafeDrive() {
                //reset_drive_encoders();
        }
        private boolean didThing = false;
        @Override public void init() {
                super.init();
                reset_drive_encoders();
                didThing = false;
        }
        @Override public void loop () {
                if(!have_drive_encoders_reset()){
                        return;
                }else if(!didThing){
                        run_using_encoders();
                        didThing = true;
                }
                run_using_encoders();
                float l_gp1_left_trigger = gamepad1.left_trigger;
                float l_gp1_right_trigger = gamepad1.right_trigger;
                float l_gp1_left_joystick = gamepad1.left_stick_x;
                float l_gp1_right_joystick = gamepad1.right_stick_y;

                float l_gp2_left_trigger = gamepad2.left_trigger;
                float l_gp2_right_joystick_y = gamepad2.right_stick_y;
                float l_gp2_left_joystick_y = gamepad2.left_stick_y;
                float upperBin = 0;
                float lowerBin = 0;
                if(gamepad2.dpad_up){
                        upperBin = -1;
                }else if(gamepad2.dpad_down){
                        upperBin = 1;
                }
                if(gamepad2.a){
                        lowerBin = -1;
                }else if(gamepad2.b){
                        lowerBin = 1;
                }

                float speed = l_gp1_right_trigger - l_gp1_left_trigger;


                //0.0 1.0
                // -1.0, 0.0 and 1.0
                //float upperBaseSpeed = 0.25f;
                float upperSpeed = upperBin*0.3f;
                //float bucketBaseSpeed = 0.2f;
                float bucketSpeed = lowerBin*0.35f;
                /*//mamaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa OOOoooOoooooooooooooooooooooooooo
                //Arm Math
                if(l_gp2_button_a){
                    //down
                    upperSpeed = -upperBaseSpeed;
                }else if(l_gp2_button_b){
                    //up
                    upperSpeed = upperBaseSpeed;
                }else if(l_gp2_button_y){
                        upperSpeed = 1.0f;
                }else if(l_gp2_button_x){
                        upperSpeed = -1.0f;
                }

                //Bucket Math
                if(l_gp2_dpad_up){
                    bucketSpeed = bucketBaseSpeed;
                }else if(l_gp2_dpad_down){
                    bucketSpeed = -bucketBaseSpeed;
                }
                double bucketPos = 0.5 - (l_gp2_left_trigger/2);*/
                //Arm Buttons





                //Drive Math
                double left_speed = Math.min(1.0, Math.max(-1.0, (speed + l_gp1_left_joystick)));
                double right_speed = Math.min(1.0, Math.max(-1.0, (speed - l_gp1_left_joystick)));

                set_drive_power(left_speed*0.45, right_speed*0.45);
                //bottom-x, bottom-y, upper-y, bucket-y
                if(get_real_arm_encoder() > 1200 && upperSpeed > 0|| get_real_arm_encoder() < 0 && upperSpeed < 0){
                        upperSpeed = 0;
                }
                if(get_real_claw_encoder() > 0 && bucketSpeed > 0|| get_real_claw_encoder() < -550 && bucketSpeed < 0){
                        bucketSpeed = 0;
                }
                set_arm_motors(upperSpeed, bucketSpeed);
                //set_arm_servo(bucketPos);
                //set_arm_servos
                update_telemetry();
                telemetry.addData("10", "GP1 Left: " + l_gp1_left_trigger);
                telemetry.addData("11", "GP1 Right: " + l_gp1_right_trigger);
                telemetry.addData("12","arm: " + get_real_arm_encoder());
                telemetry.addData("12","claw: " + get_real_claw_encoder());

        }
}