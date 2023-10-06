package custom;

import java.util.List;

public class 筛选装备_法师 extends 筛选装备_子类 {
    public String[] 要的词缀 = {"攻击速度", "幸运一击", "暴击几率", "暴击伤害", "--点力量", "点敏捷", "点意力", "点全属性", "核心技能伤害", "抗性", "伤害减免", "易伤伤害"
            , "生命上限", "非物理伤害", "距敌人的伤害", "法力", "冷却时间缩减", "敌人的伤害减免", "双持", "总护甲", "%伤害", "资源生成", "消耗", "--闪电伤害", "--电冲技能",
            "--连锁闪电", "寒冰碎片", "--电花", "寒霜技能", "冰霜伤害", "--冰霜球", "--爆焰", "--火焰", "--召唤", "--对燃烧", "--对冻伤","移动速度"};
    public String[] 不要的词缀 = {"受伤状态下的", "对受伤敌人的暴击几率", "基础技能", "法力上限"};

    public String[] 必须的词缀 = {};
    public int 需求词条数量_要求 = 3;
    public int 数值大于多少算优秀 = -10;
    public int 物品强度大于多少算优秀 = 684;

    @Override
    public int 数值大于多少算优秀() {
        return 数值大于多少算优秀;
    }

    public int 物品强度大于多少算优秀() {
        return 物品强度大于多少算优秀;
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
    public String[] 必须的词缀() {
        return 必须的词缀;
    }

    @Override
    public int 需求词条数量_要求() {
        return 需求词条数量_要求;
    }

    @Override
    public void 装备分类(List<String> result, 筛选装备.当前装备情况 当前装备情况) {
        System.out.println(result);
        for (String extractedText : result) {
            if (当前装备情况.预类别 == 筛选装备.预类别_枚举.戒指 || 当前装备情况.预类别 == 筛选装备.预类别_枚举.护符) {
                当前装备情况.装备种类 = 筛选装备.装备种类_枚举.筛选;
                当前装备情况.筛选哪些.add(筛选装备.筛选哪些_枚举.属性);
                break;
            } else if (extractedText.contains("魔杖") || extractedText.contains("匕首")) {
                当前装备情况.物品强度大于多少算优秀 = 804;
                当前装备情况.装备种类 = 筛选装备.装备种类_枚举.筛选;
                当前装备情况.筛选哪些.add(筛选装备.筛选哪些_枚举.物品强度);
                break;
            } else if (extractedText.contains("聚能器")) {
                当前装备情况.物品强度大于多少算优秀 = 797;
                当前装备情况.装备种类 = 筛选装备.装备种类_枚举.筛选;
                当前装备情况.筛选哪些.add(筛选装备.筛选哪些_枚举.物品强度);
                break;
            } else if (extractedText.contains("杖")) {
                当前装备情况.装备种类 = (筛选装备.装备种类_枚举.不要);
                break;
            } else if (extractedText.contains("手套")) {
                当前装备情况.装备种类 = 筛选装备.装备种类_枚举.筛选;
                当前装备情况.筛选哪些.add(筛选装备.筛选哪些_枚举.属性);
                break;
            }else if (extractedText.contains("靴子")) {
                当前装备情况.必须的词缀_容器=new String[]{"移动速度"};
                当前装备情况.物品强度大于多少算优秀 = 770;
                当前装备情况.装备种类 = 筛选装备.装备种类_枚举.筛选;
                当前装备情况.筛选哪些.add(筛选装备.筛选哪些_枚举.属性);
                当前装备情况.筛选哪些.add(筛选装备.筛选哪些_枚举.物品强度);
                当前装备情况.筛选哪些.add(筛选装备.筛选哪些_枚举.必须属性);
                break;
            } else if (extractedText.contains("护甲值")) {
                当前装备情况.物品强度大于多少算优秀 = 770;
                当前装备情况.装备种类 = 筛选装备.装备种类_枚举.筛选;
                当前装备情况.筛选哪些.add(筛选装备.筛选哪些_枚举.属性);
                当前装备情况.筛选哪些.add(筛选装备.筛选哪些_枚举.物品强度);
                break;
            } else {
                当前装备情况.装备种类 = (筛选装备.装备种类_枚举.未定种类);
            }
        }
    }


    @Override
    public void 自定筛选(筛选装备.当前装备情况 当前装备情况) {
        boolean 自定要求是否满足 = false;
        for (String s : 当前装备情况.是词缀的部分_容器) {
            if (s.contains("移动速度")) {
                自定要求是否满足 = true;
            }
        }

//        筛选逻辑参数.需求词条要求数量--;
        筛选装备.筛选逻辑(当前装备情况);

        if ((当前装备情况.需求词条数量是否满足)
                && 自定要求是否满足
        ) {
            当前装备情况.所有要求满足 = true;
        }
    }
}
