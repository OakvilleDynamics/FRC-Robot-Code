// RobotBuilder Version: 3.1
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;

public class TankDrive extends CommandBase {

    private final Drivetrain m_drivetrain;

    private final XboxController driverController = new XboxController(Constants.driverControllerPort);

    private boolean timerOn = false;

    // Creates a new TankDrive
    public TankDrive(Drivetrain subsystem) {
        m_drivetrain = subsystem;
        addRequirements(m_drivetrain);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        m_drivetrain.startTimer();
        timerOn = true;
        System.out.println("timer started lets gooo");
        // m_drivetrain.drive(0.3, 0.3);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        // Runs a drive command on the driverController

        m_drivetrain.drive(m_drivetrain.leftDriveStick(driverController.getY(Hand.kLeft)),
                m_drivetrain.rightDriveStick(driverController.getY(Hand.kRight)));

        System.out.println("LEFT driverController Y AXIS: " + driverController.getY(Hand.kLeft));
        System.out.println("RIGHT driverController Y AXIS: " + driverController.getY(Hand.kRight));

        if (driverController.getStartButton()) {
            m_drivetrain.startTimer();
            timerOn = true;
            System.out.println("TIMER STARTED");
        }

        if (driverController.getBumper(Hand.kRight)) {
            m_drivetrain.setPartyMode(!m_drivetrain.getPartyMode());
            System.out.println(m_drivetrain.getPartyMode() ? "PARTY MODE IS ENABLED" : "PARTY MODE IS DISABLED");
        }

        if (driverController.getAButton()) {
            if (m_drivetrain.getPartyMode()) {
                m_drivetrain.drive(Constants.partyModeLimit, Constants.partyModeLimit);
            } else {
                System.out.println("PARTY MODE IS NOT ACTIVATED, PRESS THE RIGHT BUMPER TO ACTIVATE");
            }
        }

        if (timerOn) {
            if (m_drivetrain.checkTimer() >= Constants.autoTimerSeconds) {
                m_drivetrain.stopTimer();
                timerOn = false;
            }
        }

    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        // m_drivetrain.drive(0.0, 0.0);
        System.out.println("TankDrive interrupted");
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}
