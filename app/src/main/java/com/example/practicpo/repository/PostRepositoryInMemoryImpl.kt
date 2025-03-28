package com.example.practicpo.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.practicpo.activity.Post

 class PostRepositoryInMemoryImpl : PostRepository {
    private var nextId = 1L
    private var posts = listOf(
        Post(
            id = nextId++,
            author = "БТПИТ. Университет интернет-профориентации",
            content = "Освоение новой профессии — это не только открывающиеся возможности и перспективы, но и настоящий вызов самому себе. Приходится выходить из зоны комфорта и перестраивать привычный образ жизни: менять распорядок дня, искать время для занятий, быть готовым к возможным неудачам в начале пути. В блоге рассказали, как избежать стресса на курсах профпереподготовки → http://netolo.gy/fPD",
            published = "23 сентября 2022 в 10:12",
            likedByMe = false,
            video = "https://yandex.ru/video/preview/10521616147400397285"
        ),
        Post(
            id = nextId++,
            author = "БТПИТ. Университет интернет-профориентации",
            content = "Делиться впечатлениями о любимых фильмах легко, а что если рассказать так, чтобы все заскучали \uD83D\uDE34\n",
            published = "22 сентября 2022 в 10:14",
            likedByMe = false
            ,video =null
        ),
        Post(
            id = nextId++,
            author = "БТПИТ. Университет интернет-профориентации",
            content = "Таймбоксинг — отличный способ навести порядок в своём календаре и разобраться с делами, которые долго откладывали на потом. Его главный принцип — на каждое дело заранее выделяется определённый отрезок времени. В это время вы работаете только над одной задачей, не переключаясь на другие. Собрали советы, которые помогут внедрить таймбоксинг \uD83D\uDC47\uD83C\uDFFB",
            published = "22 сентября 2022 в 10:12",
            likedByMe = false
            ,video =null
        ),
        Post(
            id = nextId++,
            author = "БТПИТ. Университет интернет-профориентации",
            content = "\uD83D\uDE80 24 сентября стартует новый поток бесплатного курса «Диджитал-старт: первый шаг к востребованной профессии» — за две недели вы попробуете себя в разных профессиях и определите, что подходит именно вам → http://netolo.gy/fQ",
            published = "21 сентября 2022 в 10:12",
            likedByMe = false,video =null
        ),
        Post(
            id = nextId++,
            author = "БТПИТ. Университет интернет-профориентации",
            content = "Диджитал давно стал частью нашей жизни: мы общаемся в социальных сетях и мессенджерах, заказываем еду, такси и оплачиваем счета через приложения.",
            published = "20 сентября 2022 в 10:14",
            likedByMe = false,video =null
        ),
        Post(
            id = nextId++,
            author = "БТПИТ. Университет интернет-профориентации",
            content = "Большая афиша мероприятий осени: конференции, выставки и хакатоны для жителей Москвы, Ульяновска и Новосибирска \uD83D\uDE09",
            published = "19 сентября 2022 в 14:12",
            likedByMe = false,video =null
        ),
        Post(
            id = nextId++,
            author = "БТПИТ. Университет интернет-профориентации",
            content = "Языков программирования много, и выбрать какой-то один бывает нелегко. Собрали подборку статей, которая поможет вам начать, если вы остановили свой выбор на JavaScript.",
            published = "19 сентября 2022 в 10:24",
            likedByMe = false,video =null
        ),
        Post(
            id = nextId++,
            author = "БТПИТ. Университет интернет-профориентации",
            content = "Знаний хватит на всех: на следующей неделе разбираемся с разработкой мобильных приложений",
            published = "18 сентября 2022 в 10:12",
            likedByMe = false,video =null
        ),
        Post(
            id = nextId++,
            author = "БТПИТ. Университет интернет-профориентации",
            content = "Привет это новый курс по программированию. Привет это новый курс по программированию. Привет это новый курс по программированию. Привет это новый курс по программированию. Привет это новый курс по программированию. Привет это новый курс по программированию. Привет это новый курс по программированию. и httt./tgghfugv/ru Привет это новый курс по программированию. Привет это новый курс по программированию. Привет это новый курс по программированию. Привет это новый курс по программированию. Привет это новый курс по программированию. Привет это новый курс по программированию. Привет это новый курс по программированию. и httt./tgghfugv/ru",
            published = "02 февраля 2022 в 18:00",
            likedByMe = false,video =null
        ),
    ).reversed()

                private val data = MutableLiveData(posts)

                override fun getAll(): LiveData<List<Post>> = data

                override fun save(post: Post) {
            if (post.id == 0L) {
                // TODO: remove hardcoded author & published
                posts = listOf(
                    post.copy(
                        id = nextId++,
                        author = "Me",
                        likedByMe = false,
                        published = "now"
                    )
                ) + posts
                data.value = posts
                return
            }

            posts = posts.map {
                if (it.id != post.id) it else it.copy(content = post.content)
            }
            data.value = posts
        }

     override fun likeById(id: Long) {
         posts = posts.map {
             if (it.id != id) it else it.copy(
                 likedByMe = !it.likedByMe,
                 likes = if (it.likedByMe) it.likes - 1 else it.likes + 1
             )
         }
         data.value = posts
     }
                override fun removeById(id: Long) {
            posts = posts.filter { it.id != id }
            data.value = posts
        }
}
