package keyword;

import edu.thu.keyword.dao.DAO;
import edu.thu.keyword.main.KeywordsExtracter;

public class KeywordExtract {
	public static void test() {
		long start=System.currentTimeMillis();
		
		//KeywordsExtracter ek=new KeywordsExtracter();
		String t = "艾比湖周边地区盐碱尘暴地面过程及其机理研究";
		String c = "盐碱尘暴又称化学尘暴，由于具有化学污染、化学腐蚀和化学毒性，其危害比普通沙尘暴更为严重。目前，国内以野外观测与风洞实验相结合手段对盐碱尘暴过程的系统性深入研究基本空白。本项目拟通过实地观测、实验室模拟、分析测试等技术手段，研究艾比湖周边地区盐漠地表物质组成、理化性质、水-热-风等自然因子与盐碱尘暴的孕育关系，阐明盐漠地表盐碱尘的释放机理；测定不同类型盐漠表面盐碱尘释放强度，揭示盐碱尘释放过程的动力机制，建立盐碱尘释放强度与影响盐碱尘释放的主要因素之间的关系。观测分析盐碱尘暴输送的盐碱沙尘颗粒物在地面30米高度内的水平通量及垂直分布结构，建立通量梯度模型，并与其他区域沙尘暴研究结果进行对比；分析盐碱尘暴输送的盐碱沙尘的理化性质（化学成分、元素浓度、粒级配置和辐射性等）、垂直分布及其与尘源地盐漠物质理化特征的相关性。";
		
		System.out.println("check stopwords :" + DAO.isInStopwords("鎻愰珮"));
		String keyword = KeywordsExtracter.ExtractKey(t,c,10);
		System.out.println(":" + keyword);
		long end=System.currentTimeMillis();
		int timeMilliseconds = (int) (end - start);
		System.out.println("\n" + "time: " + (timeMilliseconds / 1000f)
				+ " seconds");
		
		start = System.currentTimeMillis();
		keyword = KeywordsExtracter.ExtractKey(t,c,10);
		System.out.println(":" + keyword);
		end=System.currentTimeMillis();
		timeMilliseconds = (int) (end - start);
		System.out.println("\n" + "time: " + (timeMilliseconds / 1000f)
				+ " seconds");
	}
}
