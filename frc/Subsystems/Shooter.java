package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {

    private DigitalInput limitSwitch;
    private PWMSparkMax shooterMotor1;
    private PWMSparkMax shooterMotor2;
    private Timer timer;

    public Shooter(int digitalInput, int shootMotor1, int shootMotor2) {
        limitSwitch = new DigitalInput(digitalInput);
        shooterMotor1 = new PWMSparkMax(shootMotor1);
        shooterMotor2 = new PWMSparkMax(shootMotor2);
        timer = new Timer();
    }

    public Shooter(DigitalInput digitalInput, PWMSparkMax shootMotor1, PWMSparkMax shootMotor2) {
        //TODO Auto-generated constructor stub
    }

    public void operate() {
        if (limitSwitch.get()) {
            // Limit switch is triggered
            if (!timer.hasElapsed(5.0)) {
                // Motors spin for 5 seconds
                shooterMotor1.set(0.5);  // Adjust speed as needed
                shooterMotor2.set(0.5);  // Adjust speed as needed
            } else {
                // Stop the motors after 5 seconds
                shooterMotor1.set(0);
                shooterMotor2.set(0);
            }
        } else {
            // Limit switch is not triggered, stop the motors
            shooterMotor1.set(0);
            shooterMotor2.set(0);

            // Reset the timer when the limit switch is released
            timer.reset();
        }
    }
}
