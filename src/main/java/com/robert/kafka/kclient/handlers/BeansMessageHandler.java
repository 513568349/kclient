package com.robert.kafka.kclient.handlers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * This class converts the JSON string to bean array, and then make them
 * available to be processed by any subclass.
 * 
 * @author Robert Lee
 * @since Aug 21, 2015
 *
 */
public abstract class BeansMessageHandler<T> extends SafelyMessageHandler {
	protected static Logger log = LoggerFactory
			.getLogger(BeansMessageHandler.class);
	private Class<T> clazz;

	public BeansMessageHandler(Class<T> clazz) {
		this.clazz = clazz;
	}

	protected void doExecute(String message) {
		List<T> beans = JSON.parseArray(message, clazz);
		doExecuteBean(beans);
	}

	protected abstract void doExecuteBean(List<T> bean);
}