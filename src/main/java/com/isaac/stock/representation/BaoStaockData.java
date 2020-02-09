package com.isaac.stock.representation;

public class BaoStaockData extends StockData {

    private String date;
    private String code; //symbol
    private double preclose;
    private double amount;
    private int adjustflag;
    private double turn;
    private int tradestatus;
    private double pctchg;
    private double pettm;
    private double pbmrq;
    private double psttm;
    private double pcfncfttm;
    private double isst;
    private String type;


    public static BaoStaockData parse(String[] record) {
        try{
            BaoStaockData data = new BaoStaockData();
            data.setDate(record[0]);
            data.setCode(record[1]);
            data.setSymbol(record[1]);
            data.setOpen(Double.parseDouble(record[2]));
            data.setHigh(Double.parseDouble(record[3]));
            data.setLow(Double.parseDouble(record[4]));
            data.setClose(Double.parseDouble(record[5]));
            data.setPreclose(Double.parseDouble(record[6]));
            data.setVolume(Double.parseDouble(record[7]));
            data.setAmount(Double.parseDouble(record[8]));
//      data.setAdjustflag(Integer.parseInt(record[9]));
//            data.setTurn(Double.parseDouble(record[10]));
//      data.setTradestatus(Double.parseDouble(record[11]));
            data.setPctchg(Double.parseDouble(record[12]));
            data.setPettm(Double.parseDouble(record[13]));
            data.setPbmrq(Double.parseDouble(record[14]));
            data.setPsttm(Double.parseDouble(record[15]));
            data.setPcfncfttm(Double.parseDouble(record[16]));
            data.setIsst(Double.parseDouble(record[17]));
            return data;
        }catch (Exception e){
            System.out.println(record);
             throw e;
        }

    }

    @Override
    public String getDate() {
        return date;
    }

    @Override
    public void setDate(String date) {
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getPreclose() {
        return preclose;
    }

    public void setPreclose(double preclose) {
        this.preclose = preclose;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getAdjustflag() {
        return adjustflag;
    }

    public void setAdjustflag(int adjustflag) {
        this.adjustflag = adjustflag;
    }

    public double getTurn() {
        return turn;
    }

    public void setTurn(double turn) {
        this.turn = turn;
    }

    public int getTradestatus() {
        return tradestatus;
    }

    public void setTradestatus(int tradestatus) {
        this.tradestatus = tradestatus;
    }

    public double getPctchg() {
        return pctchg;
    }

    public void setPctchg(double pctchg) {
        this.pctchg = pctchg;
    }

    public double getPettm() {
        return pettm;
    }

    public void setPettm(double pettm) {
        this.pettm = pettm;
    }

    public double getPbmrq() {
        return pbmrq;
    }

    public void setPbmrq(double pbmrq) {
        this.pbmrq = pbmrq;
    }

    public double getPsttm() {
        return psttm;
    }

    public void setPsttm(double psttm) {
        this.psttm = psttm;
    }

    public double getPcfncfttm() {
        return pcfncfttm;
    }

    public void setPcfncfttm(double pcfncfttm) {
        this.pcfncfttm = pcfncfttm;
    }

    public double getIsst() {
        return isst;
    }

    public void setIsst(double isst) {
        this.isst = isst;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}