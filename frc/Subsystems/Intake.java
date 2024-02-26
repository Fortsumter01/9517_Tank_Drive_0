// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Intake extends SubsystemBase {

    private static PWMSparkMax intakeMotor1;
    private static PWMSparkMax intakeMotor2;
    private static Joystick joystick;
    private static Timer timer;

    public Intake(PWMSparkMax intakeMotor1, PWMSparkMax intakeMotor2, Joystick joystick) {
        Intake.intakeMotor1 = intakeMotor1;
        Intake.intakeMotor2 = intakeMotor2;
        Intake.joystick = joystick;
        Intake.timer = new Timer();
    }

    public void operate() {


    // Intake
        if (joystick.getRawButton(2)) {
            // Button two is pressed
            if (!timer.hasElapsed(5.0)) {
                // Motors spin for 5 seconds
                intakeMotor1.set(-0.75);  // Intakemotor2 spins anticlockwise
                intakeMotor2.set(0.75); // Intakemotor2 spins clockwise
            } else {
                // Stops the motors after 5 seconds
                intakeMotor1.set(0);
                intakeMotor2.set(0);
            }
        } else {
            // If Button two is not pressed it stop the motors
            intakeMotor1.set(0);
            intakeMotor2.set(0);

            // Resets the timer when the button is released
            timer.reset(); 
        }
      }

    public void run() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }

   
}





  

