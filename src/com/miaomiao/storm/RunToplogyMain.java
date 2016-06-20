package com.miaomiao.storm;

import backtype.storm.Config;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.generated.StormTopology;
import backtype.storm.topology.TopologyBuilder;

public class RunToplogyMain {
	public static void main(String[] args) throws AlreadyAliveException, InvalidTopologyException {
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("wordSpolt", new RunSplot(), 4);
		builder.setBolt("upperbolt", new RunBolt(), 4).shuffleGrouping("wordSpolt");
		builder.setBolt("tailBolt", new RunBolt2(), 4).shuffleGrouping("upperbolt");

		StormTopology topologydemo = builder.createTopology();
		Config conf = new Config();
		conf.setNumWorkers(4);
		conf.setNumAckers(0);
		conf.setDebug(true);
		StormSubmitter.submitTopology("topologydemo", conf, topologydemo);

	}

}
