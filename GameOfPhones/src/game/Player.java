package game;

import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONObject;

public class Player {
	String playerName;
	String player_id;
	String player_name;
	Integer conquest_points;
	Integer factory_level;
	Integer studio_level;
	Integer temple_level;
	Integer lab_level;
	Integer agency_level;
	Integer artifacts;
	Integer blueprints;
	Integer fuel;
	Integer material;
	Integer luxuries;
	Integer produce;

	public Player(String player_id, String player_name,
			Integer conquest_points, Integer factory_level,
			Integer studio_level, Integer temple_level, Integer lab_level,
			Integer agency_level, Integer artifacts, Integer blueprints,
			Integer fuel, Integer material, Integer luxuries, Integer produce) {
		this.playerName = player_name;
		this.player_id = player_id;
		this.player_name = player_name;
		this.conquest_points = conquest_points;
		this.factory_level = factory_level;
		this.studio_level = studio_level;
		this.temple_level = temple_level;
		this.lab_level = lab_level;
		this.agency_level = agency_level;
		this.artifacts = artifacts;
		this.blueprints = blueprints;
		this.fuel = fuel;
		this.material = material;
		this.luxuries = luxuries;
		this.produce = produce;
	}

	public Player(String name) {
		this.player_name = name;
	}

	public ArrayList<String> getResources() {
		ArrayList<String> rValue = new ArrayList<String>();
		rValue.add("Conquest Points:" + conquest_points);
		rValue.add("Factory Level:" + factory_level);
		rValue.add("Studio Level:" + studio_level);
		rValue.add("Temple Level:" + temple_level);
		rValue.add("Lab Level:" + lab_level);
		rValue.add("Agency Level:" + agency_level);
		rValue.add("Artifacts:" + artifacts);
		rValue.add("Blueprints:" + blueprints);
		rValue.add("Fuel:" + fuel);
		rValue.add("Material:" + material);
		rValue.add("Luxuries:" + luxuries);
		rValue.add("Produce:" + produce);

		return rValue;
	}

	public ArrayList<String> getBuildings() {
		ArrayList<String> rValue = new ArrayList<String>();
		rValue.add("Factory Level:" + factory_level);
		rValue.add("Studio Level:" + studio_level);
		rValue.add("Temple Level:" + temple_level);
		rValue.add("Lab Level:" + lab_level);
		rValue.add("Agency Level:" + agency_level);
		return rValue;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getPlayer_id() {
		return player_id;
	}

	public void setPlayer_id(String player_id) {
		this.player_id = player_id;
	}

	public String getPlayer_name() {
		return player_name;
	}

	public void setPlayer_name(String player_name) {
		this.player_name = player_name;
	}

	public Integer getConquest_points() {
		return conquest_points;
	}

	public void setConquest_points(Integer conquest_points) {
		this.conquest_points = conquest_points;
	}

	public Integer getFactory_level() {
		return factory_level;
	}

	public void setFactory_level(Integer factory_level) {
		this.factory_level = factory_level;
	}

	public Integer getStudio_level() {
		return studio_level;
	}

	public void setStudio_level(Integer studio_level) {
		this.studio_level = studio_level;
	}

	public Integer getTemple_level() {
		return temple_level;
	}

	public void setTemple_level(Integer temple_level) {
		this.temple_level = temple_level;
	}

	public Integer getLab_level() {
		return lab_level;
	}

	public void setLab_level(Integer lab_level) {
		this.lab_level = lab_level;
	}

	public Integer getAgency_level() {
		return agency_level;
	}

	public void setAgency_level(Integer agency_level) {
		this.agency_level = agency_level;
	}

	public Integer getArtifacts() {
		return artifacts;
	}

	public void setArtifacts(Integer artifacts) {
		this.artifacts = artifacts;
	}

	public Integer getBlueprints() {
		return blueprints;
	}

	public void setBlueprints(Integer blueprints) {
		this.blueprints = blueprints;
	}

	public Integer getFuel() {
		return fuel;
	}

	public void setFuel(Integer fuel) {
		this.fuel = fuel;
	}

	public Integer getMaterial() {
		return material;
	}

	public void setMaterial(Integer material) {
		this.material = material;
	}

	public Integer getLuxuries() {
		return luxuries;
	}

	public void setLuxuries(Integer luxuries) {
		this.luxuries = luxuries;
	}

	public Integer getProduce() {
		return produce;
	}

	public void setProduce(Integer produce) {
		this.produce = produce;
	}

	public String toString() {
		return playerName;
	}

}
