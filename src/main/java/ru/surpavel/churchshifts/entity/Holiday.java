package ru.surpavel.churchshifts.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "HOLIDAY")
public class Holiday implements Serializable {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "DISPLAY_NAME")
	private String displayName;

	@Column(name = "CHURCH_DEVOTED")
	private boolean isChurchDevoted;

	@Column(name = "VESTMENT_COLOR")
	@Enumerated(EnumType.STRING)
	private VestmentColor vestmentColor;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Holiday holiday = (Holiday) o;
		return id != null && Objects.equals(id, holiday.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

	public static final String specialHolidays = """
			2 марта Прп. Космы Яхромского. Утреня, Часы, Изобразительны, Вечерня
			1 июля Сщмч. Василия Крылова (Яхромское благочиние) Вечерня, Полиелейная Утреня
			26 августа Сщмч. Серафима, епископа Дмитровского. Всенощное бдение
			21 октября Священномученика Ионы (Лазарева), еп. Невельского. Вечерня, Полиелейная Утреня
			27 октября ЯХРОМСКОЙ ИКОНЫ БОЖИЕЙ МАТЕРИ. Всенощное бдение
			13 ноября Священномученика Сергия Розанова (Яхромское благочиние). Вечерня, Полиелейная Утреня
			14 ноября Священномученика Александра Смирнова (Яхромское благочиние). Вечерня, Полиелейная Утреня
			16 ноября Священномученика Владимира Писарева (Яхромское благочиние). Вечерня, Полиелейная Утреня
			19 ноября Преподобномученика Варлаама (Никольского) (Яхромское благоч.) Вечерня, Полиелейная Утреня
			25 ноября Сщмч. Владимира Красновского (Яхромское благочиние). Вечерня, Полиелейная Утреня
			27 ноября Священномученика Сергия Константинова, пресвитера Яхромского. Всенощное бдение
			16 января Cщмч. Василия Холмогорова (Яхромское благочиние). Вечерня, Полиелейная Утреня""";

	public static final List<String> orthodox_reductions = Arrays.asList(
			"ап.","апп.","архиеп.","архиепп.","архим.","архимм.","бесср.","блгв.","блгвв.","блж.","блжж.","вел.","вмц.","вмцц.","вмч.","вмчч.",
			"диак.","ев.","еп.","епп.","игум.","иером.","иеросхим.","имп.","исп.","кн.","кнн.","кнг.","кнж.","митр.","митрр.","мч.","мчч.","мц.",
			"мцц.","новмч.","новосвщмч.","патр.","патрр.","прав.","правв.","пресвит.","прор.","прорр.","пророчц.","просвет.","прот.","протопресв.",
			"прмч.","прмчч.","прмц.","прмцц.","прп.","прпп.","равноап.","равноапп.","св.","свв.","свт.","свтт.","свящ.","сщмч.","сщмчч.","столпн.","страст.","схим.","чудотв.","юрод.");
}
