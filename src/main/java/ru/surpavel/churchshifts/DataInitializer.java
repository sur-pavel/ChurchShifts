package ru.surpavel.churchshifts;

import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.jeasy.random.EasyRandom;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.surpavel.churchshifts.entity.Day;
import ru.surpavel.churchshifts.entity.Holiday;
import ru.surpavel.churchshifts.entity.Servant;
import ru.surpavel.churchshifts.entity.Service;
import ru.surpavel.churchshifts.entity.VestmentColor;
import ru.surpavel.churchshifts.service.DayManager;
import ru.surpavel.churchshifts.service.HolidayManager;
import ru.surpavel.churchshifts.service.ServantManager;
import ru.surpavel.churchshifts.service.ServiceManager;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

	private final DayManager dayManager;
	private final HolidayManager holidayManager;
	private final ServantManager servantManager;
	private final ServiceManager serviceManager;
	private final Random random = new Random();
	private final EasyRandom objectGenerator = new EasyRandom();

	@Override
	public void run(String... args) throws Exception {
//		Document doc = Jsoup.connect("http://www.patriarchia.ru/bu/2022-08-22/")
//				.data("query", "Java")
//				.userAgent("Mozilla")
//				.cookie("auth", "token")
//				.timeout(3000)
//				.post();

		Document doc = Jsoup.parse(page);

		Element firstMainParagraph = Objects.requireNonNull(doc.body().getElementById("main")).getElementsByTag("p").first();
		assert firstMainParagraph != null;
		Holiday holiday = new Holiday(random.nextLong(40)+1, firstMainParagraph.data(), random.nextBoolean(), objectGenerator.nextObject(VestmentColor.class));
		Service service = new Service(random.nextLong(40)+1,
				objectGenerator.nextObject(LocalTime.class),
				Service.types.get(random.nextInt(60)),
				Set.of(objectGenerator.nextObject(Servant.class)),
				Set.of(holiday)
				);
		List<Service> services = Collections.singletonList(service);
		Day day = new Day(random.nextLong(40)+1, LocalDate.now(), services);
		dayManager.save(day);
	}

	String page = """
			<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
			<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ru" lang="ru" xmlns:og="http://ogp.me/ns#" xmlns:fb="http://www.facebook.com/2008/fbml" >
			<head>
			  <meta http-equiv="x-dns-prefetch-control" content="on" />
			  <link rel="dns-prefetch" href="http://p2.patriarchia.ru" />
			  <meta http-equiv='Content-Type' content='text/html; charset=utf-8' />
			  <meta http-equiv='Content-Language' content='ru' />
			  <title>Богослужебные указания за 22 августа 2022 года:  / Патриархия.ru</title>
			  <meta name='description' content='Богослужебные указания за 22 августа 2022 года:  / Патриархия.ru' />
			  <link rel="stylesheet" href="/css/style.css?v818" type="text/css" />
			  <link rel="stylesheet" href="/css/print.css?v3" type="text/css" media="print" />
			  <script src="/js/archive2.js" type="text/javascript"></script>
			  <link rel='shortcut icon' href='/favicon.ico' type='image/x-icon' />
			  <script type="text/javascript">
			        var writeCalendar2Args = {cid:"News", rubric:null, current:"dbxy11yxnews", date:null};
				function resize_main() {
			    var w;
			    var left_w  = document.getElementsByClassName("menubar")[0] ? document.getElementsByClassName("menubar")[0].offsetWidth : 0;
			    var right_w = document.getElementsByClassName("sidebar")[0] ? document.getElementsByClassName("sidebar")[0].offsetWidth : 0;
			    var ww = 20 + 6 + right_w + left_w;
			    if(document.body.offsetWidth <= 1000) w = (1000 - ww) +"px";
			      else if(document.body.offsetWidth >= 1256)  w = (1256 - ww) + "px";
			        else w = document.body.offsetWidth - ww + "px";
			    document.getElementById("main").style.width = w;
				}
			  </script>
			  <link rel="alternate" type="application/rss+xml" title="Все материалы"  href="http://www.patriarchia.ru/rss/rss_all.rss" />
			  <link rel="alternate" type="application/rss+xml" title="Новости"        href="http://www.patriarchia.ru/rss/rss_news.rss" />
			  <link rel="alternate" type="application/rss+xml" title="Служение Патриарха" href="http://www.patriarchia.ru/rss/rss_patriarch.rss" />
			  <link rel="alternate" type="application/rss+xml" title="Видеоматериалы" href="http://www.patriarchia.ru/rss/rss_video.rss" />
			  <link rel="alternate" type="application/rss+xml" title="Статьи"         href="http://www.patriarchia.ru/rss/rss_items.rss" />
			  <link rel="search"    type="application/opensearchdescription+xml" title="Патриархия.ru" href="http://www.patriarchia.ru/opensearch.xml" />
			  <meta property="og:title"       content="Богослужебные указания за 22 августа 2022 года:  / Патриархия.ru" />
			  <meta property="og:description" content="Богослужебные указания за 22 августа 2022 года:  / Патриархия.ru" />
			  <meta property="og:site_name"   content="Патриархия.ru" />
			  <link rel="image_src" href="http://www.patriarchia.ru/images/patr_banner_100_2.gif" />
			  <meta property="og:image"       content="http://www.patriarchia.ru/images/patr_banner_100_2.gif" />
			</head>
			<body id="Patriarchia.Ru"   onresize="resize_main();" onload="resize_main();">
			<div class="header header_ru">
			 <h1><a href="/index.html">Русская Православная Церковь<span></span></a></h1>
			 <h2>Официальный сайт Московского Патриархата<span></span></h2>
			 <div class="search">  \s
			 <form action="/search/">
			  <input name="text" type="search" onClick="if (this.value=='поиск') this.value= ''" value="поиск" size="30" class="textfield" />
			  <input name="" type="image" src="/images/search_btn3.png" alt="Найти" lass="btn" />
			 </form>
			</div>   \s
			   
			 <div class="lang"><a href="/"><img src="/images/flag_ru.gif" alt="Русская версия" title="Русская версия" width='16' height='11' /></a><a href="/ua/"><img src="/images/flag_ua.gif" alt="Украинская версия" title="Украинская версия" width='16' height='11' /></a><a href="/md/"><img src="/images/flag_md.gif" alt="Молдавская версия" title="Молдавская версия" width='16' height='11' /></a><a href="/gr/"><img src="/images/flag_gr.gif" alt="Греческая версия" title="Греческая версия" width='16' height='11' /></a><a href="/en/"><img src="/images/flag_en.gif" alt="Английская версия" title="Английская версия" width='16' height='11' /></a></div>
			</div>
			   
			<div class="menu_box"><div class="menu">
			 <ul>
			  <li id="menu_ithem_patriarch"><a href="/patriarch/">Патриарх</a></li>
			  <li id="menu_ithem_news"><a href="/db/news/">Новости</a></li>
			  <li id="menu_ithem_document"><a href="/db/document/">Документы</a></li>
			  <li id="menu_ithem_intersobor"><a href="/db/intersobor/">Межсоборное присутствие</a></li>
			  <li id="menu_ithem_publications"><a href="/db/publications/">Публикации</a></li>
			  <li id="menu_ithem_organizations"><a href="/db/organizations/">Организации</a></li>
			  <li id="menu_ithem_persons"><a href="/db/persons/">Персоналии</a></li>
			  <li id="menu_ithem_photo"><a href="/db/photo/">Фото</a></li>
			  <li id="menu_ithem_video"><a href="/db/video/">Видео</a></li>
			  <li id="menu_ithem_audio"><a href="/db/audio/">Аудио</a></li>
			  <li id="menu_ithem_annonces"><a href="/db/annonces/">Анонсы</a></li>
			  <li id="menu_ithem_vladimir_full" style="display: none; position: absolute; width: 405px; background: url('/images/gradient_bg.gif') repeat-x scroll left -86px #e2e2e2; text-shadow: 2px 1px #ccc;"><a href='http://vladimir.patriarchia.ru'>Тысячелетие преставления равноапостольного князя Владимира</a></li>
			 </ul>
			</div></div>
			<div class="sobor"><a href="/index.html" title="Главная страница"><img src="/images/sobor.gif" alt="Патриархия" title="Патриархия" /></a></div>
			<script src="http://yastatic.net/jquery/1.8.3/jquery.min.js"></script>
			<script>
			  $(document).ready(function(){\s
			    $('#menu_ithem_vladimir_small, #menu_ithem_vladimir').mouseenter(function(){
			      $("#menu_ithem_vladimir_full").fadeIn();
			    });
			    $('#menu_ithem_vladimir_full').mouseleave(function(){
			      $("#menu_ithem_vladimir_full").fadeOut();
			    });
			  });
			</script>
			<style>
			   
			</style>
			   
			   
			<div class="wrap">
			<div class="menubar">
			\s
			<ul class="submenu"><li><a href="http://www.patriarchia.ru/db/news/5743893/">Архиерейский Собор-2022</a></li>
			<li><a href="http://www.patriarchia.ru/db/news/4885853/">Архиерейский Собор-2017</a></li>
			<li><a href="http://www.patriarchia.ru/db/news/4251478/">Архиерейский Собор-2016</a></li>
			<li><a href="http://www.patriarchia.ru/db/news/2767364/">Архиерейский Собор-2013</a></li>
			<li><a href="http://www.patriarchia.ru/db/news/1392881/">Архиерейский Собор-2011</a></li>
			<li><a href="http://www.patriarchia.ru/db/news/510720/">Поместный Собор-2009</a></li>
			<li><a href="http://www.patriarchia.ru/db/news/510719/">Архиерейский Собор-2009</a></li>
			<li><a href="http://www.patriarchia.ru/db/news/224/">Патриарх</a></li>
			<li><a href="http://www.patriarchia.ru/db/news/2831736/">Высший Церковный Совет</a></li>
			<li><a href="http://www.patriarchia.ru/db/news/1113805/">Межсоборное присутствие</a></li>
			<li><a href="http://www.patriarchia.ru/db/news/230/">Синодальные учреждения</a></li>
			<li><a href="http://www.patriarchia.ru/db/news/231/">Епархии и Экзархаты</a></li>
			<li><a href="http://www.patriarchia.ru/db/news/232/">Поместные Церкви</a></li>
			<li><a href="http://www.patriarchia.ru/db/news/233/">Церковь и государство</a></li>
			<li><a href="http://www.patriarchia.ru/db/news/234/">Церковь и общество</a></li>
			<li><a href="http://www.patriarchia.ru/db/news/3608/">Наука и образование</a></li>
			<li><a href="http://www.patriarchia.ru/db/news/235/">Издательства и СМИ</a></li>
			<li><a href="http://www.patriarchia.ru/db/news/1264/">Памятные даты</a></li>
			<li><a href="http://www.patriarchia.ru/db/news/86406/">Межрелигиозные отношения</a></li>
			<li><a href="http://www.patriarchia.ru/db/news/5811842/">Гонения на христиан</a></li>
			<li><a href="http://www.patriarchia.ru/db/news/60524/">Старообрядчество</a></li></ul>
			   
			   
			\s
			   
			<div class="section">
			 <h3>Главные новости</h3>
			\s
			 <h4 class="title"><a href="/db/text/5968634.html">В день памяти святителей Московских Святейший Патриарх Кирилл совершил Литургию в Успенском соборе Московского Кремля</a></h4>
			<h4 class="title"><a href="/db/text/5969174.html">Святейший Патриарх Кирилл встретился с и.о. генерального секретаря Всемирного совета церквей протоиереем Иоанном Саукой</a></h4>
			<h4 class="title"><a href="/db/text/5968338.html">Состоялась рабочая встреча Святейшего Патриарха Кирилла с митрополитом Крутицким и Коломенским Павлом</a></h4>
			<h4 class="title"><a href="/db/text/5968105.html">Состоялась встреча Святейшего Патриарха Кирилла с губернатором Архангельской области А.В. Цыбульским</a></h4>
			<h4 class="title"><a href="/db/text/5968012.html">Предстоятель Русской Церкви совершил освящение Михаило-Архангельского кафедрального собора г. Архангельска</a></h4>
			   
			</div>
			  \s
			   
			\s
			<div class="section">
			\s
			 <div class="archive">
			<h3>Архив</h3>
			<div id="arch_container"></div>
			<form>
			 <table class="calendar_select"><tr>
			  <td><select onchange="writeCalendar2('ru')" id="arch_mm" class="calendar_select"><option value="1">январь</option><option value="2">февраль</option><option value="3">март</option><option value="4">апрель</option><option value="5">май</option><option value="6">июнь</option><option value="7">июль</option><option value="8">август</option><option value="9">сентябрь</option><option selected="selected" value="10">октябрь</option><option value="11">ноябрь</option><option value="12">декабрь</option></select></td>
			  <td><select onchange="writeCalendar2('ru')" id="arch_yy" class="calendar_select"><option value="2004">2004</option><option value="2005">2005</option><option value="2006">2006</option><option value="2007">2007</option><option value="2008">2008</option><option value="2009">2009</option><option value="2010">2010</option><option value="2011">2011</option><option value="2012">2012</option><option value="2013">2013</option><option value="2014">2014</option><option value="2015">2015</option><option value="2016">2016</option><option value="2017">2017</option><option value="2018">2018</option><option value="2019">2019</option><option value="2020">2020</option><option value="2021">2021</option><option selected="selected" value="2022">2022</option></select></td>
			 </tr></table>
			</form>
			</div>
			<script type="text/javascript">
			  writeCalendar2('ru');
			</script>
			   
			</div>
			   
			   
			</div>
			   
			   
			<style>
			  .chten A { text-decoration : none; white-space: nowrap; }
			  #main a[href^="#"] { vertical-align: super; font-size: 70%; margin-left: 2px; }
			</style>
			<div class="main" id="main">
			  <div class="section">
			    <h1>Богослужебные указания за 22 августа 2022 года</h1>
			      <div style="position:relative;float:right;clear:right;"><a class="a_print" href="http://www.patriarchia.ru/bu/2022-08-22/print.html">Версия для печати</a></div>
			    <br>
			   \s
			<p><b>9. Понедельник. Попразднство Преображения Господня. Апостола Матфия.</b></p>
			<p>Прп. Макария Оредежского, Новгородского.</p>
			<p>Служба ап. Матфия полиелейная, совершается вместе со службой попразднства Преображения.</p>
			<p><b>На великой вечерне</b> «Блажен муж» – 1-й антифон.</p>
			<p>На «Господи, воззвах» стихиры на 8: праздника, глас 2-й – 3, и апостола, глас 1-й – 5 (первые две стихиры – дважды). «Слава» – апостола, глас 6-й: «Излияся благодать…», «И ныне» – праздника, глас 2-й: «Иже светом Твоим…».</p>
			<p>Вход. Прокимен дня. Паримии апостола – 3<a name='sns1' href='#txt1'><sup>[1]</sup></a>.</p>
			<p>На стиховне стихиры праздника, глас 6-й (со своими припевами). «Слава» – апостола, глас 2-й: «Ревности Божественныя исполнен…», «И ныне» – праздника, глас тот же: «Иже на горе Фаворстей…».</p>
			<p>По Трисвятом – тропарь апостола, глас 3-й. «Слава, и ныне» – тропарь праздника, глас 7-й.</p>
			<p>Отпуст: «Иже на горе Фаворстей...».</p>
			<p><b>На утрене</b> на «Бог Господь» – тропарь праздника, глас 7-й (дважды). «Слава» – тропарь апостола, глас 3-й, «И ныне» – тропарь праздника, глас 7-й.</p>
			<p>Кафизмы 4-я и 5-я. Малые ектении.</p>
			<p>По 1-м стихословии – седален апостола, глас 2-й (дважды). «Слава, и ныне» – седален праздника, глас 4-й.</p>
			<p>По 2-м стихословии – седален апостола, глас 4-й (дважды). «Слава, и ныне» – седален праздника, глас тот же.</p>
			<p>Полиелей. Величание апостола и избранный псалом. Седален апостола по полиелее, глас 8-й: «Идольскую гордыню низложив…» (дважды). «Слава, и ныне» – седален праздника, глас 4-й: «На горе Фаворстей…». Степенна – 1-й антифон 4-го гласа. Прокимен апостола, глас 4-й: «Во всю землю изыде вещание их и в концы вселенныя глаголы их»; стих: «Небеса поведают славу Божию, творение же руку Его возвещает твердь». Евангелие апостола. По 50-м псалме: «Слава» – «Молитвами святаго апостола Матфия…». Стихира апостола, глас 6-й: «Излияся благодать…» (см. славник на «Господи, воззвах»).</p>
			<p>Каноны: праздника 1-й со ирмосом на 6 (ирмосы по дважды) и апостола на 8.</p>
			<p>Библейские песни «Поем Господеви…».</p>
			<p>Катавасия Преображения: «Лицы израильтестии…».</p>
			<p>По 3-й песни – кондак и икос праздника, глас 7-й; седален апостола, глас 8-й: «Молниями твоих словес...» (дважды). «Слава, и ныне» – седален праздника, глас тот же: «Сокровенную молнию...» (см. в службе ап. Матфия).</p>
			<p><span style='font-size:small;'><i><b>Примечание.</b> Типикон умалчивает о седальне апостола, глас 2-й: «Незаходимое солнце явися...». В Минее синодального периода (см.: Минея-Август. Киев, 1893. Л. 101 об.) этот седален также отсутствует.</i></span></p>
			<p>По 6-й песни – кондак и икос апостола, глас 4-й.</p>
			<p>На 9-й песни поем «Честнейшую».</p>
			<p>По 9-й песни «Достойно есть» не поется. Светилен апостола (дважды). «Слава, и ныне» – светилен праздника: «Свете неизменный...» (см. в службе ап. Матфия).</p>
			<p>«Всякое дыхание…» и хвалитные псалмы.</p>
			<p>На хвалитех стихиры на 6: праздника, глас 2-й – 3, и апостола, глас 4-й – 3. «Слава» – апостола, глас 2-й: «Оставив земная…», «И ныне» – праздника, глас тот же: «Видеша на горе Фаворстей…».</p>
			<p>Великое славословие. По Трисвятом – тропарь апостола, глас 3-й. «Слава, и ныне» – тропарь праздника, глас 7-й.</p>
			<p>Отпуст: «Иже на горе Фаворстей...».</p>
			<p>На часах – тропарь праздника. «Слава» – тропарь апостола. Кондаки праздника и апостола читаются попеременно.</p>
			<p><b>На Литургии</b> блаженны праздника, песнь 4-я – 4 (1-го канона со ирмосом), и апостола, песнь 6-я – 4.</p>
			<p>На входе: «…преобразивыйся на горе…».</p>
			<p>По входе – тропари и кондаки:</p>
			<p>В храме Господском – тропарь праздника, тропарь апостола. «Слава» – кондак апостола, «И ныне» – кондак праздника.</p>
			<p>В храме Богородицы – тропарь праздника, тропарь храма, тропарь апостола; кондак праздника. «Слава» – кондак апостола, «И ныне» – кондак храма.</p>
			<p>В храме святого – тропарь праздника, тропарь храма, тропарь апостола; кондак храма. «Слава» – кондак апостола, «И ныне» – кондак праздника.</p>
			<p>Прокимен, аллилуиарий и причастен – праздника и апостола.</p>
			<p>Апостол и Евангелие – дня и апостола.</p>
			<p>Задостойник праздника.</p>
			<p>Отпуст: «Иже на горе Фаворстей...».</p>
			<hr>
			<br><a name='txt1' href='#sns1'>1</a> По традиции, новозаветные паримии читаются при открытых царских вратах.
			   
			    <div align='center'>
			        <a href='http://www.patriarchia.ru/bu/2022-08-21/'>&larr; предыдущий день</a> &nbsp\s
			        <a href='http://www.patriarchia.ru/bu/2022-08-23/'>следующий день &rarr;</a>
			    </div>
			  </div>
			</div>
			   
			\s
			<div class="sidebar">
			\s
			   
			<div class="section">
			 <h3>Календарь</h3>
			 <script>
			  function kld_date(date) {
			    var kld_url = "/rpc/date="+date+"/kld.xml";
			    server_call(null,{},function(resp) {
			      var resp_objects = xml_getObjectList(resp);
			      if (resp_objects && resp_objects[0] && resp_objects[0]["html"]) {
			        var container = document.getElementById('calendar_container');
			        container.innerHTML = resp_objects[0]["html"];
			      }
			    },kld_url,null,"GET",1);
			  }
			</script>
			<div id="calendar_container">
			   
			    <div class="illustr"><img src="http://p2.patriarchia.ru/522/845/1234/Header.jpg" border="0"  width="116" height="150" alt="Фома Дидим, ап." title="Фома Дидим, ап." /></div>
			   
			 <div class="calendar">
			  <div class="day"><b><a href="#" class="next_prev_kld" onClick="kld_date('2022-10-18');return false">&#8592;</a> 6 октября / 19 октября <a href="#" class="next_prev_kld" onClick="kld_date('2022-10-20');return false">&#8594;</a></b></div><br>
			  <div class="sedm">Апостола <a href="http://www.patriarchia.ru/db/text/912981.html">Фомы</a> (I).</div>
			  <div class="saints str1">Сщмч. Иоанна Рыбина пресвитера (1937).</div>
			  <div class="saints str2">Утр. – Ин., 67 зач., <a href="http://www.patriarchia.ru/bible/jn/21/index.html#jn-21.15">XXI, 15–25</a>. Лит. – Флп., 237 зач., <a href="http://www.patriarchia.ru/bible/flp/01/index.html#flp-01.12">I, 12–20</a>. Лк., 28 зач., <a href="http://www.patriarchia.ru/bible/lk/06/index.html#lk-06.46">VI, 46</a> – <a href="http://www.patriarchia.ru/bible/lk/07/index.html#lk-07.1">VII, 1</a>. Ап.: 1 Кор., 131 зач., <a href="http://www.patriarchia.ru/bible/co1/04/index.html#co1-04.9">IV, 9–16</a>. Ин., 65 зач., <a href="http://www.patriarchia.ru/bible/jn/20/index.html#jn-20.19">XX, 19–31</a>.</div>
			   
			    <div class="sluzh">
			      <a href="http://www.patriarchia.ru/bu/today">Богослужебные указания на сегодня</a>
			      &nbsp; <a href="http://www.patriarchia.ru/bu/tomorrow">на&nbsp;завтра</a>
			    </div>
			   
			  <div class="rop">Официальный церковный календарь.<br>Предоставлен Издательством Московской Патриархии &nbsp; <a href="http://www.rop.ru/" target="_blank">www.rop.ru</a>.</div>
			   
			 </div>
			   
			</div>
			   
			</div>
			   
			\s
			\s
			<div class="section">
			 <h3><a href="/db/video/">Видеоматериалы</a></h3>
			\s
			<div class="section-row">
			 <div class="illustr"><a href="/db/text/5968839.html"><img src="http://p2.patriarchia.ru/2022/10/18/1238176021/1detail20221018-VSN07180-obr.jpg" border="0"  width="170" height="113" alt="В день памяти святителей Московских Святейший Патриарх Кирилл совершил Литургию в Успенском соборе Московского Кремля" title="В день памяти святителей Московских Святейший Патриарх Кирилл совершил Литургию в Успенском соборе Московского Кремля" /></a></div>
			 <div class="photo">
			  <div class="date">18 октября 2022 г.</div>
			  <h4 class="title"><a href="/db/text/5968839.html">В день памяти святителей Московских Святейший Патриарх Кирилл совершил Литургию в Успенском соборе Московского Кремля</a></h4>
			  <div class="all_photo"><a href="/db/video/" class="a_video">Другие видеоматериалы</a></div>
			 </div>
			</div>
			   
			</div>
			   
			   
			\s
			<div class="section">
			 <h3><a href="/db/audio/">Аудиоматериалы</a></h3>
			\s
			 <div class="illustr"><a href="/db/text/5920790.html"><img src="http://p2.patriarchia.ru/2022/04/24/1238129348/1Yepizod_05.00_00_12_10.Still001.jpg" border="0"  width="170" height="96" alt="Пасхальное телеобращение Святейшего Патриарха Московского и всея Руси Кирилла" title="Пасхальное телеобращение Святейшего Патриарха Московского и всея Руси Кирилла" /></a></div>
			 <div class="photo">
			  <div class="date">24 апреля 2022 г.</div>
			  <h4 class="title"><a href="/db/text/5920790.html">Пасхальное телеобращение Святейшего Патриарха Московского и всея Руси Кирилла</a></h4>
			  <div class="all_photo"><a href="/db/audio/" class="a_audio">Другие аудиоматериалы</a></div>
			 </div>
			   
			</div>
			   
			   
			\s
			\s
			<div class="section">
			 <h3><a href="/db/interview/">Интервью</a></h3>
			\s
			 <div class="interview">
			  <ul><li><a href="/db/text/5965281.html">Протоиерей Максим Козлов: В непростые времена сохранение богословской науки очень важно для внутреннего здоровья и будущего народа</a></li>
			<li><a href="/db/text/5963896.html">Сначала — вере, потом — Отечеству</a></li>
			<li><a href="/db/text/5963830.html">Митрополит Нижегородский Георгий: «Мы верим, что Господь пребывает среди нас, когда мы обсуждаем проблемы монашества»</a></li></ul>
			 </div>
			   
			</div>
			   
			   
			\s
			<div class="section">
			\s
			\s
			<div class="rss_links">
			 <ul>
			  <li><a href="http://pda.patriarchia.ru/" target="_blank">PDA-версия сайта</a></li>
			  <li><a href="/db/banners.html">Наши баннеры</a></li>
			  <li><a href="/rss/">Экспорт новостей</a>  <a href="/rss/"><img src="/images/rss2.gif" alt="Экспорт новостей" title="Экспорт новостей" /></a></li>
			  <li><a href="http://sinfo-mp.ru/stamp/" target="_blank">Список печатных СМИ, получивших гриф "Одобрено Синодальным информационным отделом Русской Православной Церкви"</a></li>
			 </ul>
			</div>
			   
			</div>
			   
			   
			</div>
			   
			</div>
			   
			   
			<div class="footer">
			\s
			 <div class="copy">
			  &copy Patriarchia.ru , 2005 &ndash; 2022<br />
			  <span class="rights">Все права защищены</span>
			  <div>&copy Сделано в <a href="http://stackgroup.ru/">Stack Group</a></div>
			 </div>
			   
			 <div class="links"><a href="/txt/about.html">О проекте</a> | <a href="/txt/legal.html">Правовая информация</a> | <a href="/txt/contacts.html">Контакты</a></div>
			\s
			 <div class="counters">
			<a href="http://top100.rambler.ru/top100/"><img src="http://top100-images.rambler.ru/top100/banner-88x31-rambler-gray2.gif" alt="Rambler's Top100" width=88 height=31 border=0></a><a href="http://top100.rambler.ru/top100/"><img src="http://counter.rambler.ru/top100.cnt?730049" alt="" width=1 height=1 border=0></a><!-- Yandex.Metrika counter --><div style="display:none;"><script type="text/javascript">(function(w, c) { (w[c] = w[c] || []).push(function() { try { w.yaCounter13010452 = new Ya.Metrika({id:13010452, trackLinks:true, clickmap:true, trackHash:false, webvisor:false, accurateTrackBounce:false}); } catch(e) { } }); })(window, "yandex_metrika_callbacks");</script></div><script src="//mc.yandex.ru/metrika/watch.js" type="text/javascript" defer="defer"></script><noscript><div><img src="//mc.yandex.ru/watch/13010452" style="position:absolute; left:-9999px;" alt="" /></div></noscript><!-- /Yandex.Metrika counter -->
			 </div>
			   
			 <div class="search">  \s
			 <form action="/search/">
			  <input name="text" type="search" onClick="if (this.value=='поиск') this.value= ''" value="поиск" size="30" class="textfield" />
			  <input name="" type="image" src="/images/search_foot_btn.gif" alt="Найти" lass="btn" />
			 </form>
			</div>   \s
			   
			</div>
			   
			   
			</body>
			</html>
			   
			   
			""";
}
