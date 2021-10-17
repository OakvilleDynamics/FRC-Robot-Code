// RobotBuilder Version: 3.1
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {

  // Inits motors
  private final PWMTalonSRX leftRear;
  private final PWMTalonSRX leftFront;
  private final PWMTalonSRX rightFront;
  private final PWMTalonSRX rightRear;
  private final DifferentialDrive drive;
  private final Timer timer;
  public double leftAmount;
  public double rightAmount;
  public boolean partyMode = false;
  private SpeedControllerGroup leftMotor;
  private SpeedControllerGroup rightMotor;

  public Drivetrain() {

    // Assigns motors
    leftFront = new PWMTalonSRX(Constants.leftFrontMotorPort);
    addChild("LeftFront", leftFront);
    leftFront.setInverted(false);

    leftRear = new PWMTalonSRX(Constants.leftRearMotorPort);
    addChild("LeftRear", leftRear);
    leftRear.setInverted(false);

    SpeedControllerGroup leftMotor = new SpeedControllerGroup(leftFront, leftRear);
    addChild("LeftMotor", leftMotor);

    rightFront = new PWMTalonSRX(Constants.rightFrontMotorPort);
    addChild("RightFront", rightFront);
    rightFront.setInverted(false);

    rightRear = new PWMTalonSRX(Constants.rightRearMotorPort);
    addChild("RightRear", rightRear);
    rightRear.setInverted(false);

    SpeedControllerGroup rightMotor = new SpeedControllerGroup(rightFront, rightRear);
    addChild("RightMotor", rightMotor);

    drive = new DifferentialDrive(leftMotor, rightMotor);
    addChild("Drive", drive);
    drive.setSafetyEnabled(true);
    drive.setExpiration(0.1);

    // To change the max power, you need to change it in the Constants.java file
    if (partyMode) {
      drive.setMaxOutput(Constants.partyModeLimit);
    } else {
      drive.setMaxOutput(Constants.powerLimit);
    }

    timer = new Timer();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run when in simulation

  }

  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public void drive(double left, double right) {

    System.out.println(left);
    System.out.println(right);

    drive.tankDrive(left, right);
  }

  public double leftDriveStick(double left) {
    if (Math.abs(left) >= 0.1) {
      if (Math.abs(left) >= 0.9) {
        if (left < 0) {
          leftAmount = -Constants.fastSpeed;
        } else {
          leftAmount = Constants.fastSpeed;
        }
      } else if (left < 0) {
        leftAmount = -Constants.slowSpeed;
      } else {
        leftAmount = Constants.slowSpeed;
      }
    } else {
      leftAmount = 0;
    }
    return leftAmount;
  }

  public double rightDriveStick(double right) {
    if (Math.abs(right) >= 0.1) {
      if (Math.abs(right) >= 0.9) {
        if (right < 0) {
          rightAmount = -Constants.fastSpeed;
        } else {
          rightAmount = Constants.fastSpeed;
        }
      } else if (right < 0) {
        rightAmount = -Constants.slowSpeed;
      } else {
        rightAmount = Constants.slowSpeed;
      }
    } else {
      rightAmount = 0;
    }
    return rightAmount;
  }

  public double Test(double test) {
    return 0;
  }

  public void startTimer() {
    timer.start();
  }

  public void stopTimer() {
    timer.stop();
  }

  public double checkTimer() {
    return timer.get();
  }

  public boolean getPartyMode() {
    return partyMode;
  }

  public void setPartyMode(Boolean flag) {
    partyMode = flag;
  }
}
