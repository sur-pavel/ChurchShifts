package ru.surpavel.churchshifts.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SERVICE")
public class Service implements Serializable {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private LocalTime time;

	@Column(name = "SERVICE_TYPE")
	private String serviceType;

	@OneToMany(targetEntity = Servant.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "SERVICE", nullable = false)
	@ToString.Exclude
	private Set<Servant> servants;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "HOLIDAY_SERVICE",
			joinColumns = @JoinColumn(name = "SERVICE", nullable = false),
			inverseJoinColumns = @JoinColumn(name = "HOLIDAY", nullable = false)
	)
	@ToString.Exclude
	private Set<Holiday> holidays;

	public static List<String> types;

	static {
		types = new ArrayList<>();
		types.add("Акафист Св. Страстям Христовым (Пассия)");
		types.add("Архиерейская Литургия");
		types.add("Вечерня с выносом Плащаницы, Повечерие");
		types.add("Вечерня, Парастас");
		types.add("Вечерня, Полиелейная Утреня");
		types.add("Вечерня, Утреня");
		types.add("Вечерня, Чин прощения");
		types.add("Водосвятный молебен");
		types.add("Всенощное бдение");
		types.add("Всенощное бдение, вынос Креста");
		types.add("Всенощное бдение, освящение ваий");
		types.add("Детская Литургия");
		types.add("Заупокойные Вечерня, Утреня");
		types.add("Исповедь, Чтение Деяний");
		types.add("Крестный ход, Утреня, Часы");
		types.add("Литургия");
		types.add("Литургия, Великое освящение воды");
		types.add("Литургия, Вечерня, Крестный ход");
		types.add("Литургия, Водосвятный молебен");
		types.add("Литургия, Крестный ход");
		types.add("Литургия, Молебен, Лития");
		types.add("Литургия, Панихида");
		types.add("Литургия, Чин Торжества Православия");
		types.add("Литургия, освящение ваий");
		types.add("Молебен");
		types.add("Новогодний молебен");
		types.add("Ночная Литургия");
		types.add("Освящение куличей и пасок до 17-00");
		types.add("Пасхальная Литургия");
		types.add("Пасхальная вечерня");
		types.add("Пасхальные Вечерня, Утреня");
		types.add("Пасхальные Часы, Литургия, Крестный ход");
		types.add("Повечерие с каноном Андрея Критского");
		types.add("Повечерие, Заупокойная Утреня");
		types.add("Повечерие, Полиелейная Утреня");
		types.add("Повечерие, Утреня");
		types.add("Повечерие, Утреня 12-ти Страстных Евангелий");
		types.add("Поздняя Литургия");
		types.add("Полиелейная Утреня, 1-й Час");
		types.add("Полунощница");
		types.add("Ранняя Литургия");
		types.add("Соборная Литургия");
		types.add("Таинство Соборования");
		types.add("Утреня");
		types.add("Утреня Великого канона (Мариино стояние)");
		types.add("Утреня с Акафистом Пресвятой Богородице");
		types.add("Утреня с Чином Погребения");
		types.add("Утреня, 1-й Час");
		types.add("Утреня, Часы, Изобразительны, Вечерня");
		types.add("Утреня, Часы, Лит. Преждеосвящ. Даров");
		types.add("Утреня, Часы, Лит.Прежд.Даров, осв. колива");
		types.add("Утреня, Часы, Литургия, Крестный ход");
		types.add("Царские Часы");
		types.add("Царские Часы, Вечерня, Литургия");
		types.add("Царские Часы, Вечерня, Литургия, Великое освящение воды");
		types.add("Часы, Вечерня, Литургия");
		types.add("Часы, Изобразительны, Вечерня");
		types.add("Часы, Литургия Преждеосвященных Даров");
		types.add("Часы, Литургия, Крестный ход, Водосвятие");
		types.add("Чин Погребения");
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Service service = (Service) o;
		return id != null && Objects.equals(id, service.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
