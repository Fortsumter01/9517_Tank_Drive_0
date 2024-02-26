// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import frc.Subsystems.AmpMotorSubsystem;
import frc.Subsystems.DrivetrainSubsystem;
import frc.Subsystems.Intake;
import frc.Subsystems.ServoSubsystem;
import frc.Subsystems.Shooter;
import frc.robot.Constants.DriveConstants;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;
/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  //Defined Joystick
  Joystick joystick = new Joystick(0);
  
  //right side motor drive
  private PWMSparkMax right1 = new PWMSparkMax(DriveConstants.kRightMotor1Port);
  private PWMSparkMax right2 = new PWMSparkMax(DriveConstants.kRightMotor2Port);

  //left side motor dirve
  private PWMSparkMax left1 = new PWMSparkMax(DriveConstants.kLeftMotor1Port);
  private PWMSparkMax left2 = new PWMSparkMax(DriveConstants.kLeftMotor2Port);

  //INTAKE
  private final PWMSparkMax intakeMotor1 = new PWMSparkMax(4);
  private final PWMSparkMax intakeMotor2 = new PWMSparkMax(5);
  

  //SHOOTER
  private final PWMSparkMax shootMotor1 = new PWMSparkMax(6);
  private final PWMSparkMax shootMotor2 = new PWMSparkMax(7);

  //AMP
  private final PWMSparkMax ampMotor = new PWMSparkMax(8);

  //FLICKER SERVO
  private final Servo servo1 = new Servo(9); 

  //Subsystems

  //DrivetrainSubsystems
  private DrivetrainSubsystem drivetrain;
  //intakeSubsystems
  private Intake intakeSubsystem;
  //shooterSubsystems
  private Shooter shooterSubsystem;
  //ServoSubsystems
  private ServoSubsystem ServoSubsystem;
  //AmpMotorSubsystems
  private AmpMotorSubsystem AmpSubsystem;

  @Override
  public void robotInit() {
 
    //drivetrain sbsystem inatiallization
    drivetrain = new DrivetrainSubsystem(new DifferentialDrive(left1, right1), joystick);
    
    intakeSubsystem = new Intake(intakeMotor1, intakeMotor2, joystick);

    shooterSubsystem = new Shooter(new DigitalInput(0), shootMotor1, shootMotor2);

    ServoSubsystem = new ServoSubsystem(servo1, joystick);
    
    AmpSubsystem = new AmpMotorSubsystem(new DigitalInput(1), ampMotor);

   
    //Grouped motor(replaced motorControllerGroup command to addfollower)
    right1.addFollower(right2);
    left1.addFollower(left2);

    //inverted right motor
    right1.setInverted(true);

  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
   
    //DrivetrainSubsystem
    drivetrain.arcadeDrive();    

    // To rotate the robot in one place
    double rotate = joystick.getRawAxis(2); // For rotation control

  if (Math.abs(rotate) > 0.1) {
    // Rotates in one stationary position
    drivetrain.rotateInPlace(rotate);
  } else {
    // Uses the arcade drive if z-axis is not active
    drivetrain.arcadeDrive();
  }

  //intakeSubsystems
  intakeSubsystem.run();

  //shooterSubsystem
  shooterSubsystem.operate();

  //ServoSubsystems
  ServoSubsystem.operate();

  //AmpMotorSubsystems
  AmpSubsystem.operate();
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
