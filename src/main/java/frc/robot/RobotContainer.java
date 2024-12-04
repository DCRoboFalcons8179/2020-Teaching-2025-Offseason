// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.ControllerConstants;
import frc.robot.commands.Conveyor;
import frc.robot.commands.Drive;
import frc.robot.commands.Shoot;
import frc.robot.commands.Tilt;
import frc.robot.subsystems.Move;
import frc.robot.subsystems.SubConveyor;
import frc.robot.subsystems.SubShooter;
import frc.lib.math.Filter;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogTrigger;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Move move = new Move();
  private final SubConveyor subConveyor = new SubConveyor();
  private final SubShooter subShooter = new SubShooter();

  // Controller bindings
  private final Joystick m_driverController =
      new Joystick(ControllerConstants.kDriverControllerPort);

  DigitalInput topButton = new DigitalInput(3);
  Trigger topTrigger = new Trigger(() -> topButton.get());
  private final JoystickButton aButton = new JoystickButton(m_driverController, XboxController.Button.kA.value);   
  private final JoystickButton xButton = new JoystickButton(m_driverController, XboxController.Button.kX.value);
  private final JoystickButton yButton = new JoystickButton(m_driverController, XboxController.Button.kY.value);
  private final JoystickButton bButton = new JoystickButton(m_driverController, XboxController.Button.kB.value);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();

    // Sets the default commands for subsystems
    setDefaultCommands();
  }

  public void setDefaultCommands() {
    move.setDefaultCommand(new Drive(
    () -> m_driverController.getRawAxis(XboxController.Axis.kLeftY.value),
    () -> Filter.powerCurve(m_driverController.getRawAxis(XboxController.Axis.kRightX.value), 3),
    move));
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // A button
    aButton.whileTrue(new Conveyor(() -> 0.75, subConveyor));
    aButton.whileFalse(new Conveyor(() -> 0, subConveyor));
    
    
    // X Button
    xButton.whileTrue(new Shoot(() -> 0.5, subShooter));
    xButton.whileFalse(new Shoot(() -> 0, subShooter));

    // Tilt Up
    yButton.whileTrue(new Tilt(() -> 0.25, subShooter));
    yButton.whileFalse(new Tilt(() -> 0, subShooter));

    // Tilt Down
    bButton.whileTrue(new Tilt(() -> -0.25, subShooter));
    bButton.whileFalse(new Tilt(() -> 0, subShooter));
    
    // When top button pressed, shoot go
    topTrigger.whileFalse(new Shoot(() -> 1, subShooter));
    topTrigger.whileTrue(new Shoot(() -> 0, subShooter));

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return null;
  }
}
