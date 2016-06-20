package com.miaomiao.storm;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;

public class RunBolt2 extends BaseBasicBolt {
	private FileWriter fw;

	@Override
	public void prepare(Map stormConf, TopologyContext context) {
		try {
			fw = new FileWriter("/home/hadoop/stormdemo/" + UUID.randomUUID());
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	@Override
	public void execute(Tuple tuple, BasicOutputCollector collector) {
		String tupleName = tuple.getString(0);
		try {
			fw.write(tupleName);
			fw.write("\n");
			fw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declare) {
		declare.declare(new Fields("phoneName"));
	}

}
