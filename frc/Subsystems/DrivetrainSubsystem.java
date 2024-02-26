// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Joystick;

public class DrivetrainSubsystem extends SubsystemBase {

    private final DifferentialDrive differentialDrive;
    private final Joystick joystick;

    public DrivetrainSubsystem(DifferentialDrive differentialDrive, Joystick joystick) {
        this.differentialDrive = differentialDrive;
        this.joystick = joystick;
        addChild("DifferentialDrive", differentialDrive);
    }

    public void arcadeDrive() {
        double forward = -joystick.getRawAxis(1); 
        double rotate = joystick.getRawAxis(0);
        differentialDrive.arcadeDrive(forward, rotate);
       
    }

    public void stop() {
        differentialDrive.arcadeDrive(0, 0);
    }

    public void rotateInPlace(double rotate) {
        throw new UnsupportedOperationException("Unimplemented method 'rotateInPlace'");
    }
}
