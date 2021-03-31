package com.ofy.sdgquizapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ofy.sdgquizapp.R;

public class GlobalGoals extends AppCompatActivity {

    private ScrollView goalsScroll;
    private TextView GoalTitle, GoalDes1, GoalDes2, GoalDes3,GoalDes4, FactHead1, FactDes1, FactHead2, FactDes2, FactHead3, FactDes3,FactHead4, FactDes4, FactHead5, FactDes5, FactHead6, FactDes6;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_globalgoals);


        goalsScroll = findViewById(R.id.scGoals);
        GoalTitle = findViewById(R.id.tvGoalTitle);
        GoalDes1 = findViewById(R.id.tvDes1);
        GoalDes2 = findViewById(R.id.tvDes2);
        GoalDes3 = findViewById(R.id.tvDes3);
        GoalDes4 = findViewById(R.id.tvDes4);
        FactHead1 = findViewById(R.id.tvFactHead1);
        FactHead2 = findViewById(R.id.tvFactHead2);
        FactHead3 = findViewById(R.id.tvFactHead3);
        FactHead4 = findViewById(R.id.tvFactHead4);
        FactHead5 = findViewById(R.id.tvFactHead5);
        FactHead6 = findViewById(R.id.tvFactHead6);
        FactDes1 = findViewById(R.id.tvFactDes1);
        FactDes2 = findViewById(R.id.tvFactDes2);
        FactDes3 = findViewById(R.id.tvFactDes3);
        FactDes4 = findViewById(R.id.tvFactDes4);
        FactDes5 = findViewById(R.id.tvFactDes5);
        FactDes6 = findViewById(R.id.tvFactDes6);

        Intent intent = getIntent();

        String goal = intent.getStringExtra("goalno");


        switch (goal) {
            case "goal1":

                goalsScroll.setBackgroundColor(getResources().getColor(R.color.goal1));
                GoalTitle.setText("Goal 1: No poverty");
                GoalDes1.setText("Eradicating poverty in all its forms remains one of the greatest challenges facing humanity. While the number of people living in extreme poverty dropped by more than half between 1990 and 2015, too many are still struggling for the most basic human needs.");
                GoalDes2.setText("As of 2015, about 736 million people still lived on less than US$1.90 a day; many lack food, clean drinking water and sanitation. Rapid growth in countries such as China and India has lifted millions out of poverty, but progress has been uneven. Women are more likely to be poor than men because they have less paid work, education, and own less property.");
                GoalDes3.setText("Progress has also been limited in other regions, such as South Asia and sub-Saharan Africa, which account for 80 percent of those living in extreme poverty. New threats brought on by climate change, conflict and food insecurity, mean even more work is needed to bring people out of poverty.");
                GoalDes4.setText("The SDGs are a bold commitment to finish what we started, and end poverty in all forms and dimensions by 2030. This involves targeting the most vulnerable, increasing basic resources and services, and supporting communities affected by conflict and climate-related disasters.");
                FactHead1.setText("736 million");
                FactDes1.setText("736 million people still live in extreme poverty.");
                FactHead2.setText("10%");
                FactDes2.setText("10 percent of the world’s population live in extreme poverty, down from 36 percent in 1990.");
                FactHead3.setText("1.3 billion");
                FactDes3.setText("Some 1.3 billion people live in multidimensional poverty.");
                FactHead4.setText("50%");
                FactDes4.setText("Half of all people living in poverty are under 18.");
                FactHead5.setText("1 in 10");
                FactDes5.setText("One person in every 10 is extremely poor.");
                FactHead6.setText("80%");
                FactDes6.setText("80 percent of people living on less than $1.90 are in South Asia and sub-Saharan Africa.");

                break;
            case "goal2":

                goalsScroll.setBackgroundColor(getResources().getColor(R.color.goal2));
                GoalTitle.setText("Goal 2: Zero hunger");
                GoalDes1.setText("The number of undernourished people has dropped by almost half in the past two decades because of rapid economic growth and increased agricultural productivity. Many developing countries that used to suffer from famine and hunger can now meet their nutritional needs. Central and East Asia, Latin America and the Caribbean have all made huge progress in eradicating extreme hunger.");
                GoalDes2.setText("Unfortunately, extreme hunger and malnutrition remain a huge barrier to development in many countries. There are 821 million people estimated to be chronically undernourished as of 2017, often as a direct consequence of environmental degradation, drought and biodiversity loss.");
                GoalDes3.setText("Over 90 million children under five are dangerously underweight. Undernourishment and severe food insecurity appear to be increasing in almost all regions of Africa, as well as in South America.");
                GoalDes4.setText("The SDGs aim to end all forms of hunger and malnutrition by 2030, making sure all people–especially children–have sufficient and nutritious food all year. This involves promoting sustainable agricultural, supporting small-scale farmers and equal access to land, technology and markets. It also requires international cooperation to ensure investment in infrastructure and technology to improve agricultural productivity.");
                FactHead1.setText("821 million");
                FactDes1.setText("The number of undernourished people reached 821 million in 2017.");
                FactHead2.setText("63%");
                FactDes2.setText("In 2017 Asia accounted for nearly two thirds, 63 percent, of the world’s hungry.");
                FactHead3.setText("22%");
                FactDes3.setText("Nearly 151 million children under five, 22 percent, were still stunted in 2017.");
                FactHead4.setText("1 in 8");
                FactDes4.setText("More than 1 in 8 adults is obese.");
                FactHead5.setText("1 in 3");
                FactDes5.setText("1 in 3 women of reproductive age is anemic.");
                FactHead6.setText("26%");
                FactDes6.setText("26 percent of workers are employed in agriculture.");

                break;
            case "goal3":

                goalsScroll.setBackgroundColor(getResources().getColor(R.color.goal3));
                GoalTitle.setText("Goal 3: Good health and well-being");
                GoalDes1.setText("We have made great progress against several leading causes of death and disease. Life expectancy has increased dramatically; infant and maternal mortality rates have declined, we’ve turned the tide on HIV and malaria deaths have halved.");
                GoalDes2.setText("Good health is essential to sustainable development and the 2030 Agenda reflects the complexity and interconnectedness of the two. It takes into account widening economic and social inequalities, rapid urbanization, threats to the climate and the environment, the continuing burden of HIV and other infectious diseases, and emerging challenges such as noncommunicable diseases. ");
                GoalDes3.setText(" Universal health coverage will be integral to achieving SDG 3, ending poverty and reducing inequalities. Emerging global health priorities not explicitly included in the SDGs, including antimicrobial resistance, also demand action.");
                GoalDes4.setText("But the world is off-track to achieve the health-related SDGs. Progress has been uneven, both between and within countries. There’s a 31-year gap between the countries with the shortest and longest life expectancies. And while some countries have made impressive gains, national averages hide that many are being left behind. Multisectoral, rights-based and gender-sensitive approaches are essential to address inequalities and to build good health for all.");
                FactHead1.setText("400 million");
                FactDes1.setText("At least 400 million people have no basic healthcare, and 40 percent lack social protection.");
                FactHead2.setText("1.6 billion");
                FactDes2.setText("More than 1.6 billion people live in fragile settings where protracted crises, combined with weak national capacity to deliver basic health services, present a significant challenge to global health.");
                FactHead3.setText("15 million");
                FactDes3.setText("By the end of 2017, 21.7 million people living with HIV were receiving antiretroviral therapy. Yet more than 15 million people are still waiting for treatment.");
                FactHead4.setText("2 seconds");
                FactDes4.setText("Every 2 seconds someone aged 30 to 70 years dies prematurely from noncommunicable diseases - cardiovascular disease, chronic respiratory disease, diabetes or cancer.");
                FactHead5.setText("7 million");
                FactDes5.setText("7 million people die every year from exposure to fine particles in polluted air.");
                FactHead6.setText("1 in 3");
                FactDes6.setText("More than one of every three women have experienced either physical or sexual violence at some point in their life resulting in both short- and long-term consequences for their physical, mental, and sexual and reproductive health.");
                break;
            case "goal4":
                goalsScroll.setBackgroundColor(getResources().getColor(R.color.goal4));
                GoalTitle.setText("Goal 4: Quality education");
                GoalDes1.setText("Since 2000, there has been enormous progress in achieving the target of universal primary education. The total enrolment rate in developing regions reached 91 percent in 2015, and the worldwide number of children out of school has dropped by almost half. There has also been a dramatic increase in literacy rates, and many more girls are in school than ever before. These are all remarkable successes.");
                GoalDes2.setText("Progress has also been tough in some developing regions due to high levels of poverty, armed conflicts and other emergencies. In Western Asia and North Africa, ongoing armed conflict has seen an increase in the number of children out of school. This is a worrying trend.");
                GoalDes3.setText("While Sub-Saharan Africa made the greatest progress in primary school enrolment among all developing regions – from 52 percent in 1990, up to 78 percent in 2012 – large disparities still remain. Children from the poorest households are up to four times more likely to be out of school than those of the richest households. Disparities between rural and urban areas also remain high.");
                GoalDes4.setText("Achieving inclusive and quality education for all reaffirms the belief that education is one of the most powerful and proven vehicles for sustainable development. This goal ensures that all girls and boys complete free primary and secondary schooling by 2030. It also aims to provide equal access to affordable vocational training, to eliminate gender and wealth disparities, and achieve universal access to a quality higher education.");
                FactHead1.setText("91%");
                FactDes1.setText("Enrollment in primary education in developing countries has reached 91 percent.");
                FactHead2.setText("57 million");
                FactDes2.setText("Still, 57 million primary-aged children remain out of school, more than half of them in sub-Saharan Africa.");
                FactHead3.setText("1 in 4");
                FactDes3.setText("In developing countries, one in four girls is not in school.");
                FactHead4.setText("50%");
                FactDes4.setText("About half of all out-of-school children of primary school age live in conflict-affected areas.");
                FactHead5.setText("103 million");
                FactDes5.setText("103 million youth worldwide lack basic literacy skills, and more than 60 percent of them are women.");
                FactHead6.setText("6 of 10");
                FactDes6.setText("6 out of 10 children and adolescents are not achieving a minimum level of proficiency in reading and math.");
                break;
            case "goal5":
                goalsScroll.setBackgroundColor(getResources().getColor(R.color.goal5));
                GoalTitle.setText("Goal 5: Gender equality");
                GoalDes1.setText("Ending all discrimination against women and girls is not only a basic human right, it’s crucial for sustainable future; it’s proven that empowering women and girls helps economic growth and development.");
                GoalDes2.setText("UNDP has made gender equality central to its work and we’ve seen remarkable progress in the past 20 years. There are more girls in school now compared to 15 years ago, and most regions have reached gender parity in primary education.");
                GoalDes3.setText("But although there are more women than ever in the labour market, there are still large inequalities in some regions, with women systematically denied the same work rights as men. Sexual violence and exploitation, the unequal division of unpaid care and domestic work, and discrimination in public office all remain huge barriers. Climate change and disasters continue to have a disproportionate effect on women and children, as do conflict and migration.");
                GoalDes4.setText("It is vital to give women equal rights land and property, sexual and reproductive health, and to technology and the internet. Today there are more women in public office than ever before, but encouraging more women leaders will help achieve greater gender equality.");
                FactHead1.setText("77 cents");
                FactDes1.setText("Women earn only 77 cents for every dollar that men get for the same work.");
                FactHead2.setText("1 in 3");
                FactDes2.setText("35 percent of women have experienced physical and/or sexual violence.");
                FactHead3.setText("13%");
                FactDes3.setText("Women represent just 13 percent of agricultural landholders.");
                FactHead4.setText("750 million");
                FactDes4.setText("Almost 750 million women and girls alive today were married before their 18th birthday.");
                FactHead5.setText("2 of 3");
                FactDes5.setText("Two thirds of developing countries have achieved gender parity in primary education.");
                FactHead6.setText("24%");
                FactDes6.setText("Only 24 percent of national parliamentarians were women as of November 2018, a small increase from 11.3 percent in 1995.");
                break;
            case "goal6":
                goalsScroll.setBackgroundColor(getResources().getColor(R.color.goal6));
                GoalTitle.setText("Goal 6: Clean water and sanitation");
                GoalDes1.setText("Water scarcity affects more than 40 percent of people, an alarming figure that is projected to rise as temperatures do. Although 2.1 billion people have improved water sanitation since 1990, dwindling drinking water supplies are affecting every continent.");
                GoalDes2.setText("More and more countries are experiencing water stress, and increasing drought and desertification is already worsening these trends. By 2050, it is projected that at least one in four people will suffer recurring water shortages.Safe and affordable drinking water for all by 2030 requires we invest in adequate infrastructure, provide sanitation facilities, and encourage hygiene. Protecting and restoring water-related ecosystems is essential.");
                GoalDes3.setText("Ensuring universal safe and affordable drinking water involves reaching over 800 million people who lack basic services and improving accessibility and safety of services for over two billion.");
                GoalDes4.setText("In 2015, 4.5 billion people lacked safely managed sanitation services (with adequately disposed or treated excreta) and 2.3 billion lacked even basic sanitation.");
                FactHead1.setText("5.2 billion");
                FactDes1.setText("71 percent of the global population, 5.2 billion people, had safely-managed drinking water in 2015, but 844 million people still lacked even basic drinking water.");
                FactHead2.setText("2.9 billion");
                FactDes2.setText("39 percent of the global population, 2.9 billion people, had safe sanitation in 2015, but 2.3 billion people still lacked basic sanitation. 892 million people practiced open defecation.");
                FactHead3.setText("80%");
                FactDes3.setText("80 percent of wastewater goes into waterways without adequate treatment.");
                FactHead4.setText("2 billion");
                FactDes4.setText("Water stress affects more than 2 billion people, with this figure projected to increase.");
                FactHead5.setText("80%");
                FactDes5.setText("80 percent of countries have laid the foundations for integrated water resources management.");
                FactHead6.setText("70%");
                FactDes6.setText("The world has lost 70 percent of its natural wetlands over the last century.");
                break;
            case "goal7":
                goalsScroll.setBackgroundColor(getResources().getColor(R.color.goal7));
                GoalTitle.setText("Goal 7: Affordable and clean energy");
                GoalDes1.setText("Between 2000 and 2018, the number of people with electricity increased from 78 to 90 percent, and the numbers without electricity dipped to 789 million.");
                GoalDes2.setText("Yet as the population continues to grow, so will the demand for cheap energy, and an economy reliant on fossil fuels is creating drastic changes to our climate.");
                GoalDes3.setText("Investing in solar, wind and thermal power, improving energy productivity, and ensuring energy for all is vital if we are to achieve SDG 7 by 2030.");
                GoalDes4.setText("Expanding infrastructure and upgrading technology to provide clean and more efficient energy in all countries will encourage growth and help the environment.");
                FactHead1.setText("10%");
                FactDes1.setText("One out of 10 people still lacks electricity, and most live in rural areas of the developing world. More than half are in sub-Saharan Africa.");
                FactHead2.setText("73%");
                FactDes2.setText("Energy is by far the main contributor to climate change. It accounts for 73 percent of human-caused greenhouse gases.");
                FactHead3.setText("40%");
                FactDes3.setText("Energy efficiency is key; the right efficiency policies could enable the world to achieve more than 40 percent of the emissions cuts needed to reach its climate goals without new technology.");
                FactHead4.setText("2.8 billion");
                FactDes4.setText("Almost a third of the world’s population—2.8 billion—rely on polluting and unhealthy fuels for cooking.");
                FactHead5.setText("17.5%");
                FactDes5.setText("As of 2017, 17.5 percent of power was generated through renewable sources.");
                FactHead6.setText("18 million");
                FactDes6.setText("The renewable energy sector employed a record 11.5 million people in 2019. The changes needed in energy production and uses to achieve the Paris Agreement target of limiting the rise in temperature to below 2C can create 18 million jobs.");
                break;
            case "goal8":
                goalsScroll.setBackgroundColor(getResources().getColor(R.color.goal8));
                GoalTitle.setText("Goal 8: Decent work and economic growth");
                GoalDes1.setText("Over the past 25 years the number of workers living in extreme poverty has declined dramatically, despite the lasting impact of the 2008 economic crisis and global recession. In developing countries, the middle class now makes up more than 34 percent of total employment – a number that has almost tripled between 1991 and 2015.");
                GoalDes2.setText("However, as the global economy continues to recover we are seeing slower growth, widening inequalities, and not enough jobs to keep up with a growing labour force. According to the International Labour Organization, more than 204 million people were unemployed in 2015.");
                GoalDes3.setText("The SDGs promote sustained economic growth, higher levels of productivity and technological innovation. Encouraging entrepreneurship and job creation are key to this, as are effective measures to eradicate forced labour, slavery and human trafficking. ");
                GoalDes4.setText("With these targets in mind, the goal is to achieve full and productive employment, and decent work, for all women and men by 2030.");
                FactHead1.setText("5%");
                FactDes1.setText("An estimated 172 million people worldwide were without work in 2018 - an unemployment rate of 5 percent.");
                FactHead2.setText("1 million");
                FactDes2.setText("As a result of an expanding labour force, the number of unemployed is projected to increase by 1 million every year and reach 174 million by 2020.");
                FactHead3.setText("700 million");
                FactDes3.setText("Some 700 million workers lived in extreme or moderate poverty in 2018, with less than US$3.20 per day.");
                FactHead4.setText("48%");
                FactDes4.setText("Women’s participation in the labour force stood at 48 per cent in 2018, compared with 75 percent for men. Around 3 in 5 of the 3.5 billion people in the labour force in 2018 were men.");
                FactHead5.setText("2 billion");
                FactDes5.setText("Overall, 2 billion workers were in informal employment in 2016, accounting for 61 per cent of the world’s workforce.");
                FactHead6.setText("85 million");
                FactDes6.setText("Many more women than men are underutilized in the labour force—85 million compared to 55 million.");
                break;
            case "goal9":
                goalsScroll.setBackgroundColor(getResources().getColor(R.color.goal9));
                GoalTitle.setText("Goal 9: Industry, innovation and infrastructure");
                GoalDes1.setText("Investment in infrastructure and innovation are crucial drivers of economic growth and development. With over half the world population now living in cities, mass transport and renewable energy are becoming ever more important, as are the growth of new industries and information and communication technologies.");
                GoalDes2.setText("Technological progress is also key to finding lasting solutions to both economic and environmental challenges, such as providing new jobs and promoting energy efficiency.");
                GoalDes3.setText("Promoting sustainable industries, and investing in scientific research and innovation, are all important ways to facilitate sustainable development.");
                GoalDes4.setText("More than 4 billion people still do not have access to the Internet, and 90 percent are from the developing world. Bridging this digital divide is crucial to ensure equal access to information and knowledge, as well as foster innovation and entrepreneurship.");
                FactHead1.setText("2.3 billion");
                FactDes1.setText("Worldwide, 2.3 billion people lack access to basic sanitation.");
                FactHead2.setText("40%");
                FactDes2.setText("In some low-income African countries, infrastructure constraints cut businesses’ productivity by around 40 percent.");
                FactHead3.setText("2.6 billion");
                FactDes3.setText("2.6 billion people in developing countries do not have access to constant electricity.");
                FactHead4.setText("90%");
                FactDes4.setText("More than 4 billion people still do not have access to the Internet; 90 percent of them are in the developing world.");
                FactHead5.setText("2.3 million");
                FactDes5.setText("The renewable energy sectors currently employ more than 2.3 million people; the number could reach 20 million by 2030.");
                FactHead6.setText("30%");
                FactDes6.setText("In developing countries, barely 30 percent of agricultural products undergo industrial processing, compared to 98 percent high-income countries.");
                break;
            case "goal10":
                goalsScroll.setBackgroundColor(getResources().getColor(R.color.goal10));
                GoalTitle.setText("Goal 10: Reduced inequalities");
                GoalDes1.setText("Income inequality is on the rise—the richest 10 percent have up to 40 percent of global income whereas the poorest 10 percent earn only between 2 to 7 percent. If we take into account population growth inequality in developing countries, inequality has increased by 11 percent.");
                GoalDes2.setText("Income inequality has increased in nearly everywhere in recent decades, but at different speeds. It’s lowest in Europe and highest in the Middle East.");
                GoalDes3.setText("These widening disparities require sound policies to empower lower income earners, and promote economic inclusion of all regardless of sex, race or ethnicity.");
                GoalDes4.setText("Income inequality requires global solutions. This involves improving the regulation and monitoring of financial markets and institutions, encouraging development assistance and foreign direct investment to regions where the need is greatest. Facilitating the safe migration and mobility of people is also key to bridging the widening divide.");
                FactHead1.setText("22%");
                FactDes1.setText("In 2016, 22 percent of global income was received by the top 1 percent compared with 10 percent of income for the bottom 50 percent.");
                FactHead2.setText("16%");
                FactDes2.setText("In 1980, the top one percent had 16 percent of global income. The bottom 50 percent had 8 percent of income.");
                FactHead3.setText("33%");
                FactDes3.setText("Economic inequality is largely driven by the unequal ownership of capital. Since 1980, very large transfers of public to private wealth occurred in nearly all countries. The global wealth share of the top 1 percent was 33 percent in 2016.");
                FactHead4.setText("39%");
                FactDes4.setText("Under \"business as usual\", the top 1 percent global wealth will reach 39 percent by 2050.");
                FactHead5.setText("2x");
                FactDes5.setText("Women spend, on average, twice as much time on unpaid housework as men.");
                FactHead6.setText("60%");
                FactDes6.setText("Women have as much access to financial services as men in just 60 percent of the countries assessed and to land ownership in just 42 percent of the countries assessed.");
                break;
            case "goal11":
                goalsScroll.setBackgroundColor(getResources().getColor(R.color.goal11));
                GoalTitle.setText("Goal 11: Sustainable cities and communities");
                GoalDes1.setText("More than half of us  live in cities. By 2050, two-thirds of all humanity—6.5 billion people—will be urban. Sustainable development cannot be achieved without significantly transforming the way we build and manage our urban spaces.");
                GoalDes2.setText("The rapid growth of cities—a result of rising populations and increasing migration—has led to a boom in mega-cities, especially in the developing world, and slums are becoming a more significant feature of urban life.");
                GoalDes3.setText("Making cities sustainable means creating career and business opportunities, safe and affordable housing, and building resilient societies and economies.");
                GoalDes4.setText("It involves investment in public transport, creating green public spaces, and improving urban planning and management in participatory and inclusive ways.");
                FactHead1.setText("4.2 billion");
                FactDes1.setText("In 2018, 4.2 billion people, 55 percent of the world’s population, lived in cities. By 2050, the urban population is expected to reach 6.5 billion.");
                FactHead2.setText("3%");
                FactDes2.setText("Cities occupy just 3 percent of the Earth’s land but account for 60 to 80 percent of energy consumption and at least 70 percent of carbon emissions.");
                FactHead3.setText("828 million");
                FactDes3.setText("828 million people are estimated to live in slums, and the number is rising.");
                FactHead4.setText("33");
                FactDes4.setText("In 1990, there were 10 cities with 10 million people or more; by 2014, the number of mega-cities rose to 28, and was expected to reach 33 by 2018. In the future, 9 out of 10 mega-cities will be in the developing world.");
                FactHead5.setText("90%");
                FactDes5.setText("In the coming decades, 90 percent of urban expansion will be in the developing world.");
                FactHead6.setText("80%");
                FactDes6.setText("The economic role of cities is significant. They generate about 80 percent of the global GDP.");
                break;
            case "goal12":
                goalsScroll.setBackgroundColor(getResources().getColor(R.color.goal12));
                GoalTitle.setText("Goal 12: Responsible consumption and production");
                GoalDes1.setText("Achieving economic growth and sustainable development requires that we urgently reduce our ecological footprint by changing the way we produce and consume goods and resources. Agriculture is the biggest user of water worldwide, and irrigation now claims close to 70 percent of all freshwater for human use.");
                GoalDes2.setText("The efficient management of our shared natural resources, and the way we dispose of toxic waste and pollutants, are important targets to achieve this goal.");
                GoalDes3.setText("Encouraging industries, businesses and consumers to recycle and reduce waste is equally important, as is supporting developing countries to move towards more sustainable patterns of consumption by 2030.");
                GoalDes4.setText("A large share of the world population is still consuming far too little to meet even their basic needs.  Halving the per capita of global food waste at the retailer and consumer levels is also important for creating more efficient production and supply chains. This can help with food security, and shift us towards a more resource efficient economy.");
                FactHead1.setText("1.3 billion");
                FactDes1.setText("1.3 billion tonnes of food is wasted every year, while almost 2 billion people go hungry or undernourished.");
                FactHead2.setText("22%");
                FactDes2.setText("The food sector accounts for around 22 percent of total greenhouse gas emissions, largely from the conversion of forests into farmland.");
                FactHead3.setText("2 billion");
                FactDes3.setText("Globally, 2 billion people are overweight or obese.");
                FactHead4.setText("3%");
                FactDes4.setText("Only 3 percent of the world’s water is fresh (drinkable), and humans are using it faster than nature can replenish it.");
                FactHead5.setText("US$120 billion");
                FactDes5.setText("If people everywhere switched to energy efficient light bulbs, the world would save US$120 billion annually.");
                FactHead6.setText("20%");
                FactDes6.setText("One-fifth of the world’s final energy consumption in 2013 was from renewable sources.");
                break;
            case "goal13":
                goalsScroll.setBackgroundColor(getResources().getColor(R.color.goal13));
                GoalTitle.setText("Goal 13: Climate action");
                GoalDes1.setText("There is no country that is not experiencing the drastic effects of climate change. Greenhouse gas emissions are more than 50 percent higher than in 1990. Global warming is causing long-lasting changes to our climate system, which threatens irreversible consequences if we do not act.");
                GoalDes2.setText("The annual average economic losses from climate-related disasters are in the hundreds of billions of dollars. This is not to mention the human impact of geo-physical disasters, which are 91 percent climate-related, and which between 1998 and 2017 killed 1.3 million people, and left 4.4 billion injured.");
                GoalDes3.setText("The goal aims to mobilize US$100 billion annually by 2020 to address the needs of developing countries to both adapt to climate change and invest in low-carbon development.");
                GoalDes4.setText("Supporting vulnerable regions will directly contribute not only to Goal 13 but also to the other SDGs. These actions must also go hand in hand with efforts to integrate disaster risk measures, sustainable natural resource management, and human security into national development strategies. It is still possible, with strong political will, increased investment, and using existing technology, to limit the increase in global mean temperature to two degrees Celsius above pre-industrial levels, aiming at 1.5°C, but this requires urgent and ambitious collective action.");
                FactHead1.setText("+1°C");
                FactDes1.setText("As of 2017 humans are estimated to have caused approximately 1.0°C of global warming above pre-industrial levels.");
                FactHead2.setText("+20cm");
                FactDes2.setText("Sea levels have risen by about 20 cm (8 inches) since 1880 and are projected to rise another 30–122 cm (1 to 4 feet) by 2100.");
                FactHead3.setText("2050");
                FactDes3.setText("To limit warming to 1.5C, global net CO2 emissions must drop by 45% between 2010 and 2030, and reach net zero around 2050.");
                FactHead4.setText("1/3");
                FactDes4.setText("Climate pledges under The Paris Agreement cover only one third of the emissions reductions needed to keep the world below 2°C.");
                FactHead5.setText("$26 trillion");
                FactDes5.setText("Bold climate action could trigger at least US$26 trillion in economic benefits by 2030.");
                FactHead6.setText("18 million");
                FactDes6.setText("The energy sector alone will create around 18 million more jobs by 2030, focused specifically on sustainable energy.");
                break;
            case "goal14":
                goalsScroll.setBackgroundColor(getResources().getColor(R.color.goal14));
                GoalTitle.setText("Goal 14: Life Below Water");
                GoalDes1.setText("The world’s oceans – their temperature, chemistry, currents and life – drive global systems that make the Earth habitable for humankind. How we manage this vital resource is essential for humanity as a whole, and to counterbalance the effects of climate change.");
                GoalDes2.setText("Over three billion people depend on marine and coastal biodiversity for their livelihoods. However, today we are seeing 30 percent of the world’s fish stocks overexploited, reaching below the level at which they can produce sustainable yields.");
                GoalDes3.setText("Oceans also absorb about 30 percent of the carbon dioxide produced by humans, and we are seeing a 26 percent rise in ocean acidification since the beginning of the industrial revolution. Marine pollution, an overwhelming majority of which comes from land-based sources, is reaching alarming levels, with an average of 13,000 pieces of plastic litter to be found on every square kilometre of ocean.");
                GoalDes4.setText("The SDGs aim to sustainably manage and protect marine and coastal ecosystems from pollution, as well as address the impacts of ocean acidification. Enhancing conservation and the sustainable use of ocean-based resources through international law will also help mitigate some of the challenges facing our oceans.");
                FactHead1.setText("75%");
                FactDes1.setText("The ocean covers three quarters of the Earth’s surface and represents 99 percent of the living space on the planet by volume.");
                FactHead2.setText("200,000");
                FactDes2.setText("The ocean contains nearly 200,000 identified species, but actual numbers may lie in the millions.");
                FactHead3.setText("40%");
                FactDes3.setText("As much as 40 percent of the ocean is heavily affected by pollution, depleted fisheries, loss of coastal habitats and other human activities.");
                FactHead4.setText("30%");
                FactDes4.setText("The ocean absorbs about 30 percent of carbon dioxide produced by humans, buffering the impacts of global warming.");
                FactHead5.setText("3 billion");
                FactDes5.setText("More than 3 billion people depend on marine and coastal biodiversity for their livelihoods.");
                FactHead6.setText("US$3 trillion");
                FactDes6.setText("The market value of marine and coastal resources and industries is estimated at US$3 trillion per year, about 5 percent of global GDP.");
                break;
            case "goal15":
                goalsScroll.setBackgroundColor(getResources().getColor(R.color.goal15));
                GoalTitle.setText("Goal 15: Life on land");
                GoalDes1.setText("Human life depends on the earth as much as the ocean for our sustenance and livelihoods. Plant life provides 80 percent of the human diet, and we rely on agriculture as an important economic resources. Forests cover 30 percent of the Earth’s surface, provide vital habitats for millions of species, and important sources for clean air and water, as well as being crucial for combating climate change.");
                GoalDes2.setText("Every year, 13 million hectares of forests are lost, while the persistent degradation of drylands has led to the desertification of 3.6 billion hectares, disproportionately affecting poor communities.");
                GoalDes3.setText("While 15 percent of land is protected, biodiversity is still at risk. Nearly 7,000 species of animals and plants have been illegally traded. Wildlife trafficking not only erodes biodiversity, but creates insecurity, fuels conflict, and feeds corruption.");
                GoalDes4.setText("Urgent action must be taken to reduce the loss of natural habitats and biodiversity which are part of our common heritage and support global food and water security, climate change mitigation and adaptation, and peace and security.");
                FactHead1.setText("1.6 billion");
                FactDes1.setText("Around 1.6 billion people depend on forests for their livelihoods.");
                FactHead2.setText("80%");
                FactDes2.setText("Forests are home to more than 80 percent of all terrestrial species of animals, plants and insects.");
                FactHead3.setText("2.6 billion");
                FactDes3.setText("2.6 billion people depend directly on agriculture for a living.");
                FactHead4.setText("33%");
                FactDes4.setText("Nature-based climate solutions can contribute about a third of CO2 reductions by 2030.");
                FactHead5.setText("$125 trillion");
                FactDes5.setText("The value of ecosystems to human livelihoods and well-being is $US125 trillion per year.");
                FactHead6.setText("60-80%");
                FactDes6.setText("Mountain regions provide 60-80 percent of the Earth's fresh water.");
                break;
            case "goal16":
                goalsScroll.setBackgroundColor(getResources().getColor(R.color.goal16));
                GoalTitle.setText("Goal 16: Peace, justice and strong institutions");
                GoalDes1.setText("We cannot hope for sustainable development without peace, stability, human rights and effective governance, based on the rule of law. Yet our world is increasingly divided. Some regions enjoy peace, security and prosperity, while others fall into seemingly endless cycles of conflict and violence. This is not inevitable and must be addressed.");
                GoalDes2.setText("Armed violence and insecurity have a destructive impact on a country’s development, affecting economic growth, and often resulting in grievances that last for generations.");
                GoalDes3.setText("Sexual violence, crime, exploitation and torture are also prevalent where there is conflict, or no rule of law, and countries must take measures to protect those who are most at risk.");
                GoalDes4.setText("The SDGs aim to significantly reduce all forms of violence, and work with governments and communities to end conflict and insecurity. Promoting the rule of law and human rights are key to this process, as is reducing the flow of illicit arms and strengthening the participation of developing countries in the institutions of global governance.");
                FactHead1.setText("68.5 million");
                FactDes1.setText("By the end of 2017, 68.5 million people had been forcibly displaced as a result of persecution, conflict, violence or human rights violations.");
                FactHead2.setText("10 million");
                FactDes2.setText("There are at least 10 million stateless people who have been denied nationality and its related rights.");
                FactHead3.setText("US$1.26 trillion");
                FactDes3.setText("Corruption, bribery, theft and tax evasion cost developing countries US$1.26 trillion per year.");
                FactHead4.setText("49");
                FactDes4.setText("49 countries lack laws protecting women from domestic violence.");
                FactHead5.setText("46");
                FactDes5.setText("In 46 countries, women now hold more than 30 percent of seats in at least one chamber of national parliament.");
                FactHead6.setText("1 billion");
                FactDes6.setText("1 billion people are legally ‘invisible’ because they cannot prove who they are. This includes an estimated 625 million children under 14 whose births were never registered.");
                break;
            case "goal17":
                goalsScroll.setBackgroundColor(getResources().getColor(R.color.goal17));
                GoalTitle.setText("Goal 17: Partnerships for the goals");
                GoalDes1.setText("The SDGs can only be realized with strong global partnerships and cooperation. Official Development Assistance remained steady but below target, at US$147 billion in 2017.");
                GoalDes2.setText("While humanitarian crises brought on by conflict or natural disasters continue to demand more financial resources and aid. Many countries also require Official Development Assistance to encourage growth and trade.");
                GoalDes3.setText("The world is more interconnected than ever. Improving access to technology and knowledge is an important way to share ideas and foster innovation. Coordinating policies to help developing countries manage their debt, as well as promoting investment for the least developed, is vital for sustainable growth and development.");
                GoalDes4.setText("The goals aim to enhance North-South and South-South cooperation by supporting national plans to achieve all the targets. Promoting international trade, and helping developing countries increase their exports is all part of achieving a universal rules-based and equitable trading system that is fair and open and benefits all.");
                FactHead1.setText("US$5 trillion");
                FactDes1.setText("The UN Conference on Trade and Development (UNCTAD) says achieving SDGs will require US$5 trillion to $7 trillion in annual investment.");
                FactHead2.setText("US$147.2 billion");
                FactDes2.setText("Total official development assistance reached US$147.2 billion in 2017.");
                FactHead3.setText("US$613 billion");
                FactDes3.setText("In 2017, international remittances totaled US$613 billion; 76 percent of it went to developing countries.");
                FactHead4.setText("6");
                FactDes4.setText("In 2016, 6 countries met the international target to keep official development assistance at or above 0.7 percent of gross national income.");
                FactHead5.setText("US$18.2 trillion");
                FactDes5.setText("Sustainable and responsible investments represent high-potential sources of capital for SDGs. As of 2016, US$18.2 trillion was invested in this asset class.");
                FactHead6.setText("US$155.5 billion");
                FactDes6.setText("The bond market for sustainable business is growing. In 2018 global green bonds reached US$155.5billion, up 78 percent from previous year.");
                break;
        }



    }
}