package com.eru.rlbot.bot.ballchaser.v1.tactics;

import com.eru.rlbot.bot.EruBot;
import com.eru.rlbot.common.input.DataPacket;
import com.eru.rlbot.common.output.ControlsOutput;

public class ShadowTactician extends Tactician {

  ShadowTactician(EruBot bot) {
    super(bot);
  }

  @Override
  public boolean execute(DataPacket input, ControlsOutput output, Tactic nextTactic) {
    return false;
  }
}
