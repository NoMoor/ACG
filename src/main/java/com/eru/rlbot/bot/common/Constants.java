package com.eru.rlbot.bot.common;

import com.eru.rlbot.common.vector.Vector3;

/** RL Constants. */
public class Constants {

  // Reference: https://github.com/RLBot/RLBot/wiki/Useful-Game-Values
  // Field constants
  public static final int HALF_WIDTH = 4096;
  public static final int HALF_LENGTH = 5120;
  public static final int FIELD_LENGTH = 2 * HALF_LENGTH;
  public static final int FIELD_WIDTH = 2 * HALF_WIDTH;
  public static final int FIELD_HEIGHT = 2044;

  public static final Vector3 LEFT_SIDE_WALL = Vector3.of(HALF_WIDTH, 0, 1000);
  public static final Vector3 RIGHT_SIDE_WALL = Vector3.of(-1 * HALF_WIDTH, 0, 1000);

  // Ball
  public static final float BALL_SIZE = 92.75f;
  public static final float COEFFICIENT_OF_RESITUTION = .6f; // it loses 40% of the component of its velocity that's toward the surface

  // Car
  public static final float CAR_AT_REST = 16.99f; // Merc is 17.01
  public static final float CAR_HEIGHT = 36.16f;
  public static final float CAR_LENGTH = 118.01f;
  public static final float CAR_WIDTH = 84.2f;

  public static final float OCTANE_BALANCE_POINT = -1.6351f;

  // Acceleration
  public static final double GRAVITY = 650;
  public static final double BOOSTED_ACCELERATION = 991.666;
  // TODO: Use the formula below.
  public static final double ACCELERATION = 500; // Approximate: Acceleration at 1.0 throttle.
  public static final double COASTING_ACCELERATION = -525;
  public static final double BREAKING_ACCELERATION = -3500;

  public static final double JUMP_ACCELERATION_INSTANT = 300; // Directed towards the roof of the car.
  public static final double JUMP_HOLD_TIME = .2; // 200ms
  public static final double JUMP_ACCELERATION_HELD = 1400; // Directed towards the roof of the car. Not including gravity.

  /**
   * Maximum Car Angular Acceleration:
   * Yaw: 9.11 radians/s^2
   * Pitch: 12.46 radians/s^2
   * Roll: 38.34 radians/s^2
   */

  // Speed
  public static final double BOOSTED_MAX_SPEED = 2300;
  public static final double MAX_SPEED = 1410;

  public static double turnDepth(double velocity, double angle) {
    return radius(velocity) * Math.sin(angle);
  }

  public static double turnWidth(double velocity, double angle) {
    return radius(velocity) * Math.cos(angle);
  }

  public static double arcLength(double velocity, double angle) {
    return Math.abs(radius(velocity) * angle);
  }

  private static double radius(double velocity) {
    return velocity == 0 ? 0 : (1 / curvature(velocity));
  }

  public static float acceleration(double carVelocity) {
    if (carVelocity < 1400) {
      return (float) (1600 - carVelocity);
    } else if (carVelocity < 1410) {
      return (float) ((carVelocity - 1400) * 20);
    } else if (carVelocity >= 1410 && carVelocity < 2300){
      // Cannot throttle faster.
      return 0;
    }
    throw new IllegalArgumentException("What happened?!");
  }

  private static double curvature(double velocity) {
    if (0.0 <= velocity && velocity < 500.0) {
      return 0.006900 - 5.84e-6 * velocity;
    } else if (500.0 <= velocity && velocity < 1000.0) {
      return 0.005610 - 3.26e-6 * velocity;
    } else if (1000.0 <= velocity && velocity < 1500.0) {
      return 0.004300 - 1.95e-6 * velocity;
    } else if (1500.0 <= velocity && velocity < 1750.0) {
      return 0.003025 - 1.10e-6 * velocity;
    } else if (1750.0 <= velocity && velocity < 2500.0) {
      return 0.001800 - 0.40e-6 * velocity;
    } else {
      return 0.0;
    }
  }

  /** The distance an object can normally move. */
  public static final double NORMAL_EXPECTED = 200;
}
