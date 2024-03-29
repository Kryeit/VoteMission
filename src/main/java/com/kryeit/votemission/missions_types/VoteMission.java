package com.kryeit.votemission.missions_types;

import com.kryeit.Missions;
import com.kryeit.missions.MissionDifficulty;
import com.kryeit.missions.MissionManager;
import com.kryeit.missions.MissionType;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.UUID;

public class VoteMission implements MissionType {
	private static final Identifier IDENTIFIER = Missions.asResource("votes");

	@Override
	public Text description() {
		return Text.literal("Vote %s times");
	}

	public static void handleVote(UUID player) {
		MissionManager.incrementMission(player, VoteMission.class, IDENTIFIER, 1);
	}

	@Override
	public String id() {
		return "vote";
	}

	@Override
	public MissionDifficulty difficulty() {
		return MissionDifficulty.HARD;
	}

	@Override
	public int getProgress(UUID player, Identifier item) {
		return getData(player).getInt("votes");
	}

	@Override
	public void reset(UUID player, Identifier item) {
		getData(player).remove("votes");
	}

	@Override
	public void increment(int amount, Identifier item, NbtCompound data) {
		data.putInt("votes", data.getInt("votes") + 1);
	}

	@Override
	public ItemStack getItemStack(Identifier item) {
		return Items.AIR.getDefaultStack();
	}

	@Override
	public ItemStack getPreviewStack(Identifier item) {
		return Items.TOTEM_OF_UNDYING.getDefaultStack();
	}
}
