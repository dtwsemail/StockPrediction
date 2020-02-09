package com.isaac.stock.model;

import org.deeplearning4j.api.storage.StatsStorage;
import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.BackpropType;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.Updater;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.GravesLSTM;
import org.deeplearning4j.nn.conf.layers.RnnOutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.deeplearning4j.ui.api.UIServer;
import org.deeplearning4j.ui.stats.StatsListener;
import org.deeplearning4j.ui.storage.FileStatsStorage;
import org.deeplearning4j.ui.storage.InMemoryStatsStorage;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.learning.config.RmsProp;
import org.nd4j.linalg.lossfunctions.LossFunctions;

import java.io.File;

/**
 * Created by zhanghao on 26/7/17.
 * @author ZHANG HAO
 */
public class RecurrentNets {
	
	private static final double learningRate = 0.0005;
	private static final int iterations = 1;
	private static final int seed = 12345;

    private static final int lstmLayer1Size = 128;
    private static final int lstmLayer2Size = 128;
    private static final int denseLayerSize = 32;
//    private static final double dropoutRatio = 0.2;
    private static final int truncatedBPTTLength = 22;



    public static MultiLayerNetwork buildLstmNetworks(int nIn, int nOut) {


        // some common parameters
//        NeuralNetConfiguration.Builder builder = new NeuralNetConfiguration.Builder();
//        builder.seed(123);
//        builder.biasInit(0);
//        builder.miniBatch(false);
//        builder.updater(new RmsProp(0.001));
//        builder.weightInit(WeightInit.XAVIER);
        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
                .seed(seed)
//                .iterations(iterations)
//                .learningRate(learningRate)
                .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)
                .weightInit(WeightInit.XAVIER)
//                .updater(Updater.RMSPROP)
                .updater(new RmsProp(learningRate))
//                .regularization(true)
                .l2(1e-4)
                .list()
                .layer(0, new GravesLSTM.Builder()
                        .nIn(nIn)
                        .nOut(lstmLayer1Size)
                        .activation(Activation.TANH)
                        .gateActivationFunction(Activation.HARDSIGMOID)
//                        .dropOut(dropoutRatio)
                        .build())
                .layer(1, new GravesLSTM.Builder()
                        .nIn(lstmLayer1Size)
                        .nOut(lstmLayer2Size)
                        .activation(Activation.TANH)
                        .gateActivationFunction(Activation.HARDSIGMOID)
//                        .dropOut(dropoutRatio)
                        .build())
                .layer(2, new DenseLayer.Builder()
                		.nIn(lstmLayer2Size)
                		.nOut(denseLayerSize)
                		.activation(Activation.LEAKYRELU)
                		.build())
                .layer(3, new RnnOutputLayer.Builder()
                        .nIn(denseLayerSize)
                        .nOut(nOut)
                        .activation(Activation.IDENTITY)
                        .lossFunction(LossFunctions.LossFunction.MSE)
                        .build())
                .backpropType(BackpropType.TruncatedBPTT)
                .tBPTTForwardLength(truncatedBPTTLength)
                .tBPTTBackwardLength(truncatedBPTTLength)
//                .pretrain(false)
//                .backprop(true)
                .build();

        MultiLayerNetwork net = new MultiLayerNetwork(conf);
        net.init();


        //初始化用户界面后端
//        UIServer uiServer = UIServer.getInstance();
//        //设置网络信息（随时间变化的梯度、分值等）的存储位置。这里将其存储于内存。
//        StatsStorage statsStorage = new InMemoryStatsStorage();         //或者： new FileStatsStorage(File)，用于后续的保存和载入
//        //将StatsStorage实例连接至用户界面，让StatsStorage的内容能够被可视化
//        uiServer.attach(statsStorage);
//        UIServer uiServer = UIServer.getInstance();
//        StatsStorage statsStorage = new FileStatsStorage(new File(System.getProperty("java.io.tmpdir"), "ui-stats.dl4j"));
//        uiServer.attach(statsStorage);
//        new StatsListener(statsStorage),
        net.setListeners(new ScoreIterationListener(100));
        return net;
    }
}
