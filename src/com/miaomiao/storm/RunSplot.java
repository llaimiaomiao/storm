package com.miaomiao.storm;

import java.util.Map;
import java.util.Random;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import backtype.storm.utils.Utils;

public class RunSplot extends BaseRichSpout {
	private SpoutOutputCollector collector;
	String[] words = { "xiaomi", "iphone", "kangjia", "huawei", "meizu" };

	@Override
	public void nextTuple() {
		Random r = new Random();
		int nextInt = r.nextInt(words.length);
		String phoneName = words[nextInt];
		collector.emit(new Values(phoneName));
		Utils.sleep(500);
	}

	@Override
	public void open(Map arg0, TopologyContext arg1, SpoutOutputCollector collector) {
		this.collector = collector;
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declare) {
		declare.declare(new Fields("phone_name"));

	}

}
