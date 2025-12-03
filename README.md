# Smart University (No Inheritance)

**BBS 515 - Nesne Yonelimli Programlama**  
**Ogrenci:** Nurcan Denli Bayir | **No:** N25110789

---

## Kurulum / Installation

### 1. GitHub'dan Indir / Download from GitHub
```bash
git clone https://github.com/mortemdulcem/smartuniversity.git
cd smartuniversity
```

Veya ZIP olarak indir: https://github.com/mortemdulcem/smartuniversity/archive/refs/heads/main.zip

### 2. Derle ve Calistir / Build and Run

#### Windows (CMD veya PowerShell)
```cmd
mkdir out
javac -encoding UTF-8 -d out src/university/model/*.java src/university/util/*.java src/university/service/*.java src/university/Main.java
java -cp out university.Main
```

#### Mac / Linux (Terminal)
```bash
chmod +x build.sh run.sh
./build.sh
./run.sh
```

#### Alternatif (Tum Platformlar)
```bash
javac -d out -sourcepath src src/university/Main.java
java -cp out university.Main
```

---

## Komutlar / Commands

| Komut | Aciklama |
|-------|----------|
| `help` | Yardim menusunu goster |
| `add-student <id> "<isim>" "<bolum>"` | Ogrenci ekle |
| `add-course <kod> "<baslik>" <kredi> "<ogretmen>"` | Ders ekle |
| `enroll <ogrenciId> <dersKodu>` | Kayit yap |
| `grade <ogrenciId> <dersKodu> <yuzde>` | Not ver |
| `gpa <ogrenciId>` | GPA hesapla |
| `list-students` | Ogrencileri listele |
| `list-courses` | Dersleri listele |
| `list-enrollments` | Kayitlari listele |
| `save` | CSV'ye kaydet |
| `load` | CSV'den yukle |
| `exit` | Cikis |

---

## Ornek Kullanim / Example

```
> add-student 1001 "Ali Veli" "Bilgisayar Muhendisligi"
Added student.

> add-course CS101 "Programlama" 4 "Dr. Ahmet"
Added course.

> enroll 1001 CS101
Enrolled.

> grade 1001 CS101 85
Grade recorded.

> gpa 1001
GPA for 1001: 3.70

> save
Saved to ./data

> exit
Bye.
```

---

## Not Sistemi / Grading

| Yuzde | Puan |
|-------|------|
| 90-100 | 4.0 |
| 85-89 | 3.7 |
| 80-84 | 3.3 |
| 75-79 | 3.0 |
| 70-74 | 2.7 |
| 65-69 | 2.3 |
| 60-64 | 2.0 |
| 55-59 | 1.7 |
| 50-54 | 1.0 |
| 0-49 | 0.0 |

---

## Dosya Yapisi / Structure

```
smartuniversity/
├── src/university/
│   ├── model/
│   │   ├── Student.java
│   │   ├── Course.java
│   │   └── Enrollment.java
│   ├── service/
│   │   └── University.java
│   ├── util/
│   │   ├── GradeUtils.java
│   │   ├── CSVExporter.java
│   │   └── CSVImporter.java
│   └── Main.java
├── build.sh
├── run.sh
└── README.md
```

---

## Tasarim / Design

- **No Inheritance:** Hicbir `extends` veya `interface` kullanilmadi
- **Final Classes:** Tum siniflar `final` olarak tanimli
- **Encapsulation:** Tum alanlar `private`, getter/setter ile erisim
- **Composition:** Kalitim yerine bilesim tercih edildi
