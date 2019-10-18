package com.poc.deliveryapp.pojo;

import org.springframework.data.mongodb.core.mapping.Document;

@ Document
public class Player {

	private String tracked_until;
	private ProfileD profile;
	private int leaderboard_rank;
	private int solo_competitive_rank;
	private int rank_tier;
	private int competitive_rank;
	private MMR mmr_estimate;

	public String getTracked_until() {
		return tracked_until;
	}

	public void setTracked_until(String tracked_until) {
		this.tracked_until = tracked_until;
	}

	public int getSolo_competitive_rank() {
		return solo_competitive_rank;
	}

	public void setSolo_competitive_rank(int solo_competitive_rank) {
		this.solo_competitive_rank = solo_competitive_rank;
	}

	public int getLeaderboard_rank() {
		return leaderboard_rank;
	}

	public void setLeaderboard_rank(int leaderboard_rank) {
		this.leaderboard_rank = leaderboard_rank;
	}

	public ProfileD getProfile() {
		return profile;
	}

	public void setProfile(ProfileD profile) {
		this.profile = profile;
	}

	public int getRank_tier() {
		return rank_tier;
	}

	public void setRank_tier(int rank_tier) {
		this.rank_tier = rank_tier;
	}

	public int getCompetitive_rank() {
		return competitive_rank;
	}

	public void setCompetitive_rank(int competitive_rank) {
		this.competitive_rank = competitive_rank;
	}

	public MMR getMmr_estimate() {
		return mmr_estimate;
	}

	public void setMmr_estimate(MMR mmr_estimate) {
		this.mmr_estimate = mmr_estimate;
	}

}
