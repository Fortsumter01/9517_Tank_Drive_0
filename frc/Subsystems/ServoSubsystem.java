package frc.robot;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ServoSubsystem extends SubsystemBase {

    private static Servo servo;
    private static Joystick joystick;

    private static boolean buttonPressed = false;

    public ServoSubsystem(Servo servo, Joystick joystick) {
        ServoSubsystem.servo = servo;
        ServoSubsystem.joystick = joystick;
    }

    public void operate() {
        // Check if button 1 is pressed
        boolean button1Pressed = joystick.getRawButton(1);

        // If the button was not pressed before and is now pressed
        if (button1Pressed && !buttonPressed) {
            // Moves the servo up
            servo.set(1.0);
            
            // Wait for a short duration 
            try {
                Thread.sleep(500); // Sleeps for 500 milliseconds (0.5 seconds)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Move the servo back down
            servo.set(0.0);
        }

        // Update the buttonPressed variable for the next iteration
        buttonPressed = button1Pressed;
    }
}