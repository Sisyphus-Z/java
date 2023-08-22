package custom;

import java.util.List;

public class 筛选装备_野蛮人 extends 筛选装备_子类 {
    public String[] 要的词缀 = {"攻击速度", "暴击几率", "先祖之锤", "点力量", "点敏捷", "点意力", "点全属性", "核心技能伤害", "抗性", "伤害减免", "移动速度", "易伤伤害"
            , "生命上限", "物理伤害", "对近距敌人的伤害", "怒气", "冷却", "----对流血敌人的---", "敌人的伤害减免", "使用双手钝击武器的伤害","强固时","总护甲","%伤害"};
    public String[] 不要的词缀 = {"受伤状态下的伤害减免", "对受伤敌人的暴击几率","对流血敌人的"};
    public int 需求词条数量_要求 =3;

    public int 数值大于多少算优秀 = -10;

    @Override
    public int 数值大于多少算优秀() {
        return 数值大于多少算优秀;
    }


    @Override
    public String[] 要的词缀() {
        return 要的词缀;
    }

    @Override
    public String[] 不要的词缀() {
        return 不要的词缀;
    }

    @Override
    public int 需求词条数量_要求() {
        return 需求词条数量_要求;
    }

    @Override
    public void 装备分类(List<String> result, 筛选装备.筛选逻辑参数 筛选逻辑参数, String 预类别) {
        for(String extractedText:result) {
            if (extractedText.contains("双手锤")) {
                筛选逻辑参数.装备种类 = (筛选装备.装备种类.只看数值);
                break;
            } else if (extractedText.contains("靴子")) {
                筛选逻辑参数.装备种类 = (筛选装备.装备种类.自定要求);
                break;
            } else if (extractedText.contains("护甲值")) {
                筛选逻辑参数.装备种类 = (筛选装备.装备种类.只看属性);
                break;
            } else if (!extractedText.contains("双手") && (extractedText.contains("斧") || extractedText.contains("剑") || extractedText.contains("锤"))) {
                筛选逻辑参数.装备种类 = (筛选装备.装备种类.只看属性);
                break;
            } else if ((extractedText.contains("双手") && extractedText.contains("劈砍")) || extractedText.contains("长柄武器")) {
                筛选逻辑参数.装备种类 = (筛选装备.装备种类.只看属性);
                break;
            }else {
                筛选逻辑参数.装备种类 = (筛选装备.装备种类.看数值或看属性);
            }
        }

        if(预类别.equals("戒指")||预类别.equals("护符")){
            筛选逻辑参数.装备种类=(筛选装备.装备种类.只看属性);
        }
    }


    @Override
    public void 自定筛选(筛选装备.筛选逻辑参数 筛选逻辑参数) {
        筛选逻辑参数.自定要求是否满足=false;
        for (String s : 筛选逻辑参数.是词缀的部分) {
            if(s.contains("移动速度")){
                筛选逻辑参数.自定要求是否满足=true;
            }
        }

//        筛选逻辑参数.需求词条要求数量--;
        筛选装备.筛选逻辑(筛选逻辑参数);

        if((筛选逻辑参数.需求词条数量是否满足)
        &&筛选逻辑参数.自定要求是否满足
        ){
            筛选逻辑参数.所有要求满足=true;
        }
    }
}
