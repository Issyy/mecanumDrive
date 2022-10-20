// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.hal.PowerDistributionJNI;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import frc.robot.Subsystems.MecanumDriveSubsystem;

/** This is a demo program showing how to use Mecanum control with the MecanumDrive class. */
public class Robot extends TimedRobot {
  private static final int frontLeftChannel = 1;
  private static final int rearLeftChannel = 2;
  private static final int frontRightChannel = 3;
  private static final int rearRightChannel = 4;

  private static final int kJoystickChannel = 0;

  private MecanumDrive m_robotDrive;
  // private Joystick m_stick;
  WPI_TalonSRX frontLeft = new  WPI_TalonSRX(frontLeftChannel);
  WPI_TalonSRX rearLeft = new  WPI_TalonSRX(rearLeftChannel);
  WPI_TalonSRX frontRight = new  WPI_TalonSRX(frontRightChannel);
  WPI_TalonSRX rearRight = new  WPI_TalonSRX(rearRightChannel);

    // Talon frontLeft = new Tal    on(frontLeftChannel);
    // Talon rearLeft = new Talon(rearLeftChannel);
    // Talon frontRight = new Talon(frontRightChannel);
    // Talon rearRight = new Talon(rearRightChannel);

    //Compressor compressor = new Compressor(0);
  Compressor compressor = new Compressor(PneumaticsModuleType.REVPH);  

  MecanumDrive robotDrive;

  Joystick joystick = new Joystick(0);
  JoystickButton compressorButton = new JoystickButton(joystick, 2);
  MecanumDriveSubsystem mecanumDriveSubsystem;

  //Solenoid solenoid = new Solenoid(PneumaticsModuleType.REVPH, 8);
  PowerDistribution powerDistribution = new PowerDistribution(1, ModuleType.kRev);
  //PowerDistribution powerDistribution;
  //PowerDistributionJNI test = new PowerDistributionJNI();

  boolean reloaded = true;


  @Override
  public void robotInit() {    
    frontLeft.clearStickyFaults();
    rearLeft.clearStickyFaults();
    frontRight.clearStickyFaults();
    rearRight.clearStickyFaults();
    
    CameraServer.startAutomaticCapture();

    //compressor.disable();
    powerDistribution.setSwitchableChannel(false);
    //PowerDistributionJNI.initialize(0, PowerDistributionJNI.REV_TYPE);
    //powerDistribution = new PowerDistribution(1, ModuleType.kRev);
    
    

    //powerDistribution.setSwitchableChannel(enabled);
    // Invert the right side motors.
    // You may need to change or remove this to match your robot.

    //frontLeft.setInverted(invertType);
    frontRight.setNeutralMode(NeutralMode.Brake);
    frontLeft.setNeutralMode(NeutralMode.Brake);
    rearRight.setNeutralMode(NeutralMode.Brake);
    rearLeft.setNeutralMode(NeutralMode.Brake);

    //compressor.disable();
    //compressor.enableDigital();
    //powerDistribution.sw

    frontRight.setInverted(true); 
    rearRight.setInverted(true);


    robotDrive = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);

    joystick = new Joystick(kJoystickChannel);

    InitializeSubsystems();
  }

  void InitializeSubsystems()
  {
    mecanumDriveSubsystem = new MecanumDriveSubsystem(robotDrive);
  }

  @Override
  public void disabledPeriodic()
  {
    compressor.disable();
    powerDistribution.setSwitchableChannel(false);
  }

  @Override
  public void teleopPeriodic() {
    //frontRightMotor.set(ControlMode.PercentOutput, 0.1);
    // Use the joystick X axis for lateral movement, Y axis for forward
    // movement, and Z axis for rotation.%
    //System.out.println("Pressure: " + compressor.getPressure());

    //System.out.println(powerDistribution.getVoltage());
    //System.out.println("Teleop");

   System.out.println(powerDistribution.getVoltage());

    if (joystick.getRawButton(8))
    {
      //System.out.println("Trigger is pressed");
      //PowerDistributionJNI.setSwitchableChannel(1, true);
     
      // if (reloaded)
      // {
      //   reloaded = false;
      //   timer.start();
      System.out.println("Solenoid is on");
      powerDistribution.setSwitchableChannel(true);  
      // }
      

      //solenoid.set(true);
      //if (solenoid.)
      //solenoid.setPulseDuration(1);
      //solenoid.startPulse();
    } 
    else
    {
      //PowerDistributionJNI.setSwitchableChannel(1, false);

      //solenoid.set(false);
      //System.out.println("Solenoid is off");
      powerDistribution.setSwitchableChannel(false);

    }


    //System.out.println();

    //System.out.println("Valve is open: " + powerDistribution.getSwitchableChannel());
    
    if (joystick.getRawButton(1))
    {
      compressor.disable();
    }
    else if (joystick.getRawButton(3))
    {
      compressor.enableDigital();
    }

    //mecanumDriveSubsystem.driveCartesian(() -> -joystick.getPOV(), () -> joystick.getX() * 0.6, () -> joystick.getZ() * 0.6);
    mecanumDriveSubsystem.driveCartesian(() -> -joystick.getY(), () -> joystick.getX(), () -> joystick.getZ());
    //mecanumDriveSubsystem.driveCartesian(() -> 0, () -> 0, () -> 0);
    //System.out.println(joystick.getY());
  
  }
}
