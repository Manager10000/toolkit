package com.johnson.toolkit.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jhg on 2018-12-13.
 */
public class Test {

    public static void main(String[] args) {

        String str = "<a href=\"http://www.cnooc.com.cn:80/picture/11/73e04de42db944448c055267f388234e.jpg\" target=\"_blank\"><img title=\"\" style=\"border:none;\" src=\"http://www.cnooc.com.cn:80/picture/11/s_73e04de42db944448c055267f388234e.jpg\" width=\"600\" height=\"399\"/></a>记者 安磊 摄影12月12日，集团公司2018年网络安全和信息化委员会暨CIO工作会在总部召开。会议传达学习了习近平总书记网络强国战略思想，总结今年网络安全和信息化（下称网信）工作，部署2019年重点任务。集团公司党组书记、董事长、网信委员会主任杨华要求，认真贯彻落实习近平总书记网络强国战略思想，加快数字化转型，实现高质量发展。杨华指出，今年以来，集团公司信息化管理工作得到了进一步加强，集团公司四地五数据中心、各类信息系统总体运行平稳，系统可用性超过99.9%，保证了公司生产、经营管理的日常需要，重点信息化建设项目稳步推进，网信工作取得了较好的成绩。对于加强集团公司网信工作，杨华提出了要求：进一步增强推动公司数字化转型的责任感、紧迫感、使命感；进一步加强信息化顶层设计，推动信息化体制机制改革，抓好各方面工作落实，为实现公司高质量发展贡献价值；进一步提高网络安全防护能力，不断提升信息技术支撑服务水平；进一步加强网信工作体系建设和人才队伍培养，为提升网信工作水平、加快数字化转型提供保障。集团公司党组副书记、总经理、网信委员会副主任汪东进传达了习近平总书记关于网信工作的一系列重要讲话精神。集团公司党组成员、副总经理、网信委员会副主任陈壁主持会议。2018年，集团公司认真落实国家有关部署，在外部网站IPv6（互联网协议第6版）改造、“三重一大”系统监管数据和大额资金使用动态数据上报、“互联网+”扶贫、软件正版化等方面取得了积极进展；围绕核心业务开展信息系统建设，助力随钻效率提升、开拓市场、强化管理、提升服务能力；积极推动公司集中统一平台建设，促进管理提升；探索应用新技术缓解海陆通讯带宽瓶颈；按照有关部委要求开展网络安全检查、参加网络安全技能比武，提升网络安全防御能力。下一步，集团公司将结合内外部环境研究和对标分析，在补齐短板、强化优势、推动转型上下功夫，打造中国特色国际一流的集成统一共享信息平台，在此基础上，持续完善IT治理、网络安全两大保障体系，加快推动生产云、管理云、销售云三“朵”应用云的建设与应用，促进生产方式转变、销售创新突破、服务共享提质，推动数字化转型，实现高质量发展。集团公司网信委员会成员部门和单位的负责人、CIO（首席信息官）参加会议。（记者 赵国兵）";

        String regex_img = "<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>";
        Pattern imgPattern =Pattern.compile(regex_img);

        Matcher imgMatcher= imgPattern.matcher(str);
        while(imgMatcher.find()){
            System.out.println(imgMatcher.group(1));
        }




    }






}
