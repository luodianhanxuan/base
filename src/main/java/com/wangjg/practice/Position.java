package com.wangjg.practice;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * 前台统一持仓
 *
 * @author jun
 */
@Getter
@Setter
public class Position {

    private String id;
    private String rowSumType;

    /**
     * 当前日期
     */

    private Date dateCurrent;
    /**
     * 市值_期末-front
     */
    private double peMktv;
    /**
     * 期初市值-front
     */
    private double psMktv;

    /**
     * 当日盈亏-front
     */
    private double dailyPnl;

    /**
     * 当日收益率/日涨跌幅 -front
     */
    private double dailyReturn;

    private double returnRate;

    /**
     * 当日买入金额-front
     */
    private double accumBuyAmtToday;
    private Double buyAmtDay;

    /**
     * 当日卖出金额-front
     */
    private double accumSellAmtToday;
    private Double sellAmtDay;

    /**
     * 成交编号
     */
    private String executionCode;

    /**
     * 产品代码
     */
    private String fundCode;

    /**
     * 资产单元代码
     */
    private String assetUnitCode;

    /**
     * 策略代码
     */
    private String strategyCode;

    /**
     * 子账户代码
     */
    private String cashAccountCode;

    /**
     * 对手方
     */
    private String counterParty;

    /**
     * 证券代码（内部编码）
     */
    private String secuCode;

    /**
     * 应计利息_期初
     */
    private double psAi;

    /**
     * 应计利息_发生额
     */
    private double chgAi;

    /**
     * 应计利息_期末
     */
    private double peAi;

    /**
     * 交易类型（正回购_逆回购）
     */
    private String tradeTypeCode;

    /**
     * 交易日
     */
    private Date tradeDate;

    /**
     * 首期清算日
     */
    private Date firstClearDate;

    /**
     * 首期交收日
     */
    private Date firstSettleDate;

    /**
     * 到期清算日
     */
    private Date endClearDate;

    /**
     * 到期交收日
     */
    private Date endSettleDate;

    /**
     * 回购金额
     */
    private double amt;

    /**
     * 实际占用天数
     */
    private Long occupancyDays;

    /**
     * 交易市场
     */
    private String marketCode;

    /**
     * 回购利率
     */
    private double repoRate;
    private double repoReturnRate;

    /**
     * 回购期限
     */
    private Long repoTerm;

    /**
     * 持仓成本_期初
     */
    private double psCost;

    /**
     * 持仓成本_发生额
     */
    private double chgCost;

    /**
     * 持仓成本_期末
     */
    private double peCost;

    /**
     * 实际收益率
     */
    private double effectiveYield;
    /**
     * 实际利率
     */
    private double effectiveRate;
    /**
     * 证券类别
     */
    private String secuType;
    private String secuTypeCode;

    /**
     * 剩余期限
     */
    private Long residualMaturity;

    /**
     * 计入成本费用
     */
    private double feeInCost;

    /**
     * 回购净收益
     */
    private double netProfit;

    /**
     * 币种
     */
    private String currency;

    /**
     * 清算速度
     */
    private Integer settleSpeed;

    /**
     * 券商代码
     */
    private String brokerCode;

    /**
     * 交易席位
     */
    private String tradeSeat;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 证券代码
     */
    private String symbol;

    /**
     * 利息收入_期初
     */
    private double psInterestIncome;

    /**
     * 利息收入_发生额
     */
    private double chgInterestIncome;

    /**
     * 利息收入_期末
     */

    private double peInterestIncome;

    /**
     * 已付交易费用
     */

    private double paidTradingFee;

    /**
     * 应付交易费用
     */

    private double payableTradingFee;

    /**
     * 利息总额(回购收益)
     */

    private double totalInterest;

    /**
     * 质押券的secuCode,多个时候用,分隔
     */
    private String pledgeBonds;


    /**
     * 单位持仓成本
     */
    private double unitCost;
    private double peUnitCost;

    /**
     * 市场名称
     */
    private String marketName;
    /**
     * 期末市值-qfc-brinson-campisi
     */
    private double peMktValue;
    private double position;

    private double latestMktValue;

    /**
     * 期初市值-qfc-brinson-campisi
     */
    private double psMktValue;
    /**
     * qfc原position表期初市值
     */
    private double prevMktValue;
    private double mktValue;
    /**
     * old 市值
     */
    private double oldMktValue;

    /**
     * 证券名称
     */
    private String secuName;
    /**
     * 债券类别1
     */
    private String fiClass1;
    private String fiClass1Name;
    /**
     * 债券类别2
     */
    private String fiClass2;
    private String fiClass2Name;
    /**
     * 债券类别3
     */
    private String fiClass3;
    /**
     * 债券类别3
     */
    private String fiClass3Name;
    /**
     * 债券类别4
     */
    private String fiClass4;
    private String fiClass4Name;
    /**
     * 股票大盘中盘小盘
     */
    private String stockIssuedTypeCode;
    /**
     * 股票大盘中盘小盘
     */
    private String stockIssuedTypeName;
    /**
     * 股票板块
     */
    private String stockBoardCode;
    /**
     * 股票板块名称
     */
    private String stockBoardName;
    /**
     * 股票风格
     */
    private String stockStyle;
    /**
     * 股票行业
     */
    private String stockIndustryName;
    /**
     * 股票行业代码
     */
    private String stockIndustryCode;

    /**
     * 持仓
     */
    private double pePosCostLocal;
    /**
     * 债券外部评级
     */
    private String bondExternalRating;
    /**
     * 债券内部评级
     */
    private String bondInternalRating;
    /**
     * 发行人外部评级
     */
    private String issuerExternalRating;
    /**
     * 发行人内部评级
     */
    private String issuerInternalRating;
    /**
     * 票息率
     */
    private double couponRate;

    /**
     * YTM--qfc认为该字段已经作废，切换成lastYtm,而持仓分析显示的时候显示的是ytm,所以 查到qfc数据后就进行了转换
     */
    private double ytm;
    private double latestYtm;

    /**
     * 剩余期限
     */
    private double oldResidualMaturity;
    /**
     * 剩余期限-qfc
     */
    private double remainingPeriod;
    /**
     * 剩余存续期
     */
    private double residualHoldingPeriod;
    /**
     * 到期日
     */
    private Date maturityDate;
    /**
     * DV01
     */
    private double dv01;
    /**
     * 凸性
     */
    private double convexity;
    /**
     * 修正久期
     */
    private double duration;

    /**
     * 修正久期贡献
     */
    private double durationContrib;


    /**
     * 名称
     */
    private String name;
    /**
     * 证券代码
     */
    private String code;
    /**
     * 价格
     */
    private double mktPrice;
    /**
     * 债券净价
     */
    private double cleanPrice;
    /**
     * 债券全价
     */
    private double dirtyPrice;
    /**
     * 数量
     */
    private double qty;


    /**
     * 全价市值
     */
    private double mktValueDirty;

    private double oldMktValueDirty;
    /**
     * 修正久期
     */
    private double oldDuration;
    /**
     * 票息率
     */
    private double oldCouponRate;
    /**
     * 当日盈亏
     */
    private double oldDailyPnl;
    /**
     * 单位持仓成本
     */
    private double oldUnitCost;
    /**
     * 板块内累计占比
     */
    private double oldWeightAccClassify;
    /**
     * 占净值比例-针对整个产品
     */
    private double netWeight;
    /**
     * 板块内累计占比、分类内占比
     */
    private double weightAccClassify;

    /**
     * 净值占比（不同券类型）--股票，债券
     */
    private double weightByType;
    /**
     * 交易场所
     */
    private String marketPlace;

    /**
     * 总成本
     */
    private double totalCost;

    /**
     * old总成本
     */
    private double oldTotalCost;

    /**
     * 估值损益_发生额 （6101）
     */
    private Double chgValueGl;

    /**
     * 投资收益_发生额 （6111）
     */
    private Double chgInvestementIncome;
    /**
     * 折溢价_发生额
     */
    private Double chgDiscountPremium;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 日涨跌净值
     */
    private double returnValue;


    /**
     * 最新价
     */
    private double latestMktPrice;
    /**
     * 占总资产比例*
     */
    private double totalWeight;

    /**
     * 应计利息
     */
    private double ai;
    /**
     * 首期交易日
     */
    private Date firstTradeDate;

    /**
     * 到期交易日
     */
    private Date endTradeDate;
    /**
     * 到期交收日
     */
    private Date endTransDate;
    /**
     * 实际占用天数
     */
    private Integer occupiedDays;
    /**
     * 交易费用
     */
    private double tradeFee;
    private Double peTradingFee;
    /**
     * 当日买入交易费用
     */
    private Double buyTradingFeeDay;
    /**
     * 当日卖出交易费用
     */
    private Double sellTradingFeeDay;

    /**
     * 存款利率
     */
    private double depositRate;
    /**
     * 起息日
     */
    private Date orgDate;
    /**
     * 计息天数
     */
    private Integer interestDay;
    /**
     * 提前支取利率
     */
    private double withdrawInAdvanceRate;
    /**
     * 每日计提
     */
    private double interestProfitDay;
    /**
     * 累计盈亏
     */
    private double peAccumPnlLocal;
    /**
     * 累计盈亏
     */
    private double peAccumPnl;


    /**
     * 计息方式
     */
    private String interestFormula;
    /**
     * 存款金额(元)
     */
    private double depositAmt;
    /**
     * 关键利率久期
     */
    private KrdBeanOutProp krdBean;
    /**
     * 打新收益
     */
    private double ipoPnl;
    /**
     * 当日已实现盈亏
     */
    private double dailyRealizedPnl;

    /**
     * 当日未实现盈亏,当日价差浮盈
     */
    private double dailyUnRealizedPnl;

    /**
     * 当日收益占净资产比
     */
    private double dailyPnlWeightToNetAsset;

    /**
     * 当日未实现盈亏,当日价差浮盈
     */
    private double oldDailyUnRealizedPnl;
    /**
     * 累计盈亏
     */
    private double oldPeAccumPnlLocal;
    /**
     * 占总资产比例*
     */
    private double oldTotalWeight;
    /**
     * 当日已实现盈亏
     */
    private double oldDailyRealizedPnl;
    /**
     * 当日收益占净资产比
     */
    private double oldDailyPnlWeightToNetAsset;
    /**
     * 占净资产比例-股票
     */
    private double percentToNetAssetEquity;
    /**
     * 当日买入数量
     */
    private double accumBuyQtyDay;


    /**
     * 当日卖出数量
     */
    private double accumSellQtyDay;

    /**
     * 日初单位成本
     */
    private double psUnitCostLocal;
    /**
     * 当日单位成本变化
     */
    private double posCosChgtLocal;
    /**
     * 首次建仓日期
     */
    private Date firstBuyDate;
    /**
     * 偏离度
     */
    private double deviation;
    /**
     * 当日应计利息
     */
    private double dailyAccruedInterest;
    /**
     * 发行机构
     */
    private String issuer;
    /**
     * 股票行业代码
     */
    private String industryCode;
    /**
     * 股票行业名称
     */
    private String industryName;
    /**
     * 板块代码
     */
    private String boardCode;
    /**
     * 板块名称
     */
    private String boardName;
    /**
     * 风格代码
     */
    private String styleCode;
    /**
     * 风格名称
     */
    private String styleName;
    /**
     * 板块
     */
    private String currenceIssue;
    /**
     * 久期-翻译
     */
    private String durationDesc;
    private String durationCode;
    /**
     * 评级-外部
     */
    private String ratingExternal;
    /**
     * 评级-内部
     */
    private String ratingInside;
    /**
     * 剩余期限 翻译
     */
    private String residualMaturityDesc;
    private String residualMaturityDescCode;
    /**
     * 累计净值占比--各个类型
     */
    private double weightAccByType;

    private List<Position> children;
    /**
     * 模拟试算 持仓类型PositionType
     *
     * @see
     */
    private int dataModel;
    private String classifyCode;
    private String classifyCode2;
    private double cleanMktValue;
    private double netAsset;
    private double peTotalAsset;
    /**
     * 回购交易类型
     */
    private String repoType;
    /**
     * 占净资产比例
     */
    private double oldNetWeight;
    private double valuationMktPrice;
    /**
     * 估值全价市值
     */
    private double valuationMktValue;
    /**
     * 估值全价市值old
     */
    private double oldValuationMktValue;
    /**
     * 当日收益占净资产比例
     */
    private double dailyPnlNetWeight;

    private double oldDailyPnlNetWeight;

    private String CSName;
    /**
     * 估值全价
     */
    private double oldDirtyPrice;
    /**
     * 数量
     */
    private double oldQty;
    /**
     * class1
     */
    private String class1;
    /**
     * class2
     */
    private String class2;
    /**
     * class3
     */
    private String class3;

    /**
     * 持有类型
     */
    private String assetStatus;


    /**
     * 明细状态
     */
    private String propreStatus;


    /**
     * 估值数量
     */
    private Long peQty;

    /**
     * 市场净价
     */
    private double marketPrice;

    /**
     * 估值价
     */
    private double valuationPrice;

    /**
     * 昨日市场净价
     */
    private double prevMarketPrice;

    /**
     * 市场全价
     */
    private double makretFullPrice;

    /**
     * 昨日市场全价
     */
    private double prevMakretFullPrice;

    /**
     * 昨日估值价
     */
    private double prevValuationPrice;


    /**
     * 总持仓成本
     */
    private double posCost;


    /**
     * 前一日净价市值
     */
    private double prevCleanMktValue;

    /**
     * 占比债券类资产权重（全价）
     */
    private double weightForAllocationAsset;

    /**
     * 累积买入金额
     */
    private double accumBuyAmount;

    /**
     * 累积卖出金额
     */
    private double accumSellAmount;


    /**
     * 前一日今年以来收益
     */
    private double prevYtdReturn;

    /**
     * 今年以来收益
     */
    private double ytdReturn;

    /**
     * 前一日建仓以来收益
     */
    private double prevCumulReturn;

    /**
     * 建仓以来收益
     */
    private double cumulReturn;

    /**
     * 影子市值
     */
    private double shadowMktValue;

    /**
     * 期末应计利息
     */
    private double accruedInterest;


    /**
     * 母组合代码
     */
    private String parentPortfolioCode;


    /**
     * 占比净资产权重（全价）
     */
    private double weightForNetAsset;

    /**
     * 占比总资产权重（全价）
     */
    private double weightForTotalAsset;


    /**
     * 收益贡献
     */
    private double dailyContribution;

    /**
     * 期末摊余收入
     */
    private double peAmorIncomeLocal;

    private Date createTime;


    /**
     * 来自IBOR持仓表：累计盈亏_变化
     */
    private double chgAccumPnl;

    /**
     * 影子价格
     */
    private double shadowPrice;

    /**
     * 来自IBOR持仓表：当日交易费_期末
     */
    private double unrealizedSpreadGlToday;

    /**
     * 来自IBOR持仓表：当日已实现价差损益
     */
    private double realizedSpreadGlToday;

    private double tradeFeeToday;


    /**
     * 市盈率
     */
    private double pe;

    /**
     * 市净率
     */
    private double pb;

    /**
     * 波动率
     */
    private double volatility;

    /**
     * 换手率
     */
    private double turnover;

    /**
     * 对冲比例
     */
    private double hedgeRatio;


    /**
     * 当日已实现变化股息损益_原币
     */
    private double chgRealizedDivGlLocal;

    /**
     * 期初累计已实现股息损益_原币
     */
    private double psAccumRealizedDivGlLocal;

    /**
     * 期末累计已实现股息损益_原币
     */
    private double peAccumRealizedDivGlLocal;

    /**
     * Delta值
     */
    private double deltaVal;

    /**
     * Theta值
     */
    private double thetaVal;

    /**
     * Gamma值
     */
    private double gammaVal;

    /**
     * Vega值
     */
    private double vegaVal;

    /**
     * Rho值
     */
    private double rhoVal;

    /**
     * DividendRho值
     */
    private double dividendRhoVal;

    /**
     * IntrinsicValue值
     */
    private double intrinsicVal;


    /**
     * 存款类型->估值表存款类型
     */
    private String depositType;

    /**
     * 起息日->估值表起息日
     */
    private Date effectiveDate;


    /**
     * 存款利率->估值表存款利率
     */
    private double interestRate;


    /**
     * 存款金额->估值表存款金额
     */
    private double balanceAmt;


    /**
     * 合同编号
     */
    private String contractCode;

    /**
     * 资产分类<>vmob=vmob=证券
     */
    private String assetClassificationCode;

    /**
     * 期权分类<>EUROPEAN=EUROPEAN=可选欧式=AMERICAN=美式
     */
    private String optionType;
    /**
     * 期权结构<>VANILLA=VANILLA=可选香草=BARRIER=障碍
     */
    private String optionStructure;
    /**
     * 标的代码 （可多标的）
     */
    private String underlyingCode;

    /**
     * call/put<>CALL=CALL=call=OUT=put
     */
    private String callPut;
    /**
     * 方向<>BUY=BUY=buy=SELL=sell
     */
    private String buySell;
    /**
     * S0标的价格 标的开仓时的价格
     */
    private double s0Price;
    /**
     * 执行价（元）
     */
    private double strikePrice;
    /**
     * 行权比例（%），默认100.
     */
    private double strikePct;
    /**
     * 标的数量
     */
    private double totalOptions;
    /**
     * 名义本金（元）
     */
    private double notionalAmt;
    /**
     * 开仓日
     */
    private Date startDate;
    /**
     * 到期日
     */
    private Date expiryDate;
    /**
     * 期权价格（%）
     */
    private double optionPricePct;
    /**
     * 期权价格（元）
     */
    private double optionPrice;
    /**
     * PREMIUM. 具体见后面每个交易对其产生的影响，开仓时+
     */
    private double premiumLocal;
    /**
     * 无风险利率（%）
     */
    private double riskFreeRate;
    /**
     * 交易对手
     */
    private String counterpartyCode;

    /**
     * 交易币种
     */
    private String tradeCurrHcode;
    /**
     * 结算币种
     */
    private String settleCurrHcode;
    /**
     * 估值币种
     */
    private String bookCurrHcode;
    /**
     * 交易汇率
     */
    private double tradeFx;
    /**
     * 结算汇率
     */
    private double settleFx;
    /**
     * 市场汇率
     */
    private double exchangeRate;
    /**
     * 上次平仓日期
     */
    private Date lastCloseDate;
    /**
     * 上次平仓价格（%）
     */
    private double lastClosePricePct;
    /**
     * 上次平仓价格（元）
     */
    private double lastClosePrice;
    /**
     * 上次平仓数量。默认为0，平仓交易进入时计算。
     */
    private double lastCloseQty;
    /**
     * 上次平仓金额
     */
    private double lastCloseAmt;
    /**
     * 期末累计平仓数量
     */
    private double peAccumCloseQty;
    /**
     * 期末累计平仓金额
     */
    private double peAccumCloseAmt;
    /**
     * 期末持仓数量
     */
    private double peValuationQty;
    private double peValuationPrice;
    /**
     * 外部参考号，通常为外部合同编号
     */
    private String refOut;
    /**
     * PricingVolatility值
     */
    private double pricingvolatilityVal;


    /**
     * 估值数量->股票估值表期末数量->PE_VALUATION_QTY
     */
    private double valuationQty;

    /**
     * 正回购
     */
    public final static String REPO_NAME = "正回购";

    /**
     * 逆回购
     */
    public final static String REVERSE_REPURCHASE_NAME = "逆回购";


    /**
     * @description: 关键利率久期 <br>
     * @date: 2019/8/5 14:52 <br>
     * @author: llj <br>
     * @version: 1.0 <br>
     */
    @Getter
    @Setter
    class KrdBeanOutProp {

        /**
         * 3M
         */
        private double krd3m;
        /**
         * 1Y
         */
        private double krd1y;
        /**
         * 2Y
         */
        private double krd2y;
        /**
         * 3Y
         */
        private double krd3y;
        /**
         * 5Y
         */
        private double krd5y;
        /**
         * 7Y
         */
        private double krd7y;
        /**
         * 10Y
         */
        private double krd10y;
        /**
         * 15Y
         */
        private double krd15y;
        /**
         * 25Y
         */
        private double krd25y;
        /**
         * 30Y
         */
        private double krd30y;
        /**
         * 20y
         */
        private double krd20y;


    }


    /**
     * 存单编号
     */

    private String depositCode;


    /**
     * 存款银行
     */

    private String bankPartyHcode;

    /**
     * 存款分支行
     */

    private String bankBranchPartyHcode;


    /**
     * 起息日
     */

    private Date valueDate;

    /**
     * 到期日
     */

    private Date dueDate;

    /**
     * 计息天数
     */

    private Integer interestDays;


    /**
     * 存款日期
     */

    private Date depositDate;


}
