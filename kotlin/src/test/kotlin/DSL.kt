import org.junit.jupiter.api.Test

// https://kotlinlang.org/docs/type-safe-builders.html
class DSL {
    fun something(input: Int) = Unit

    @Test
    fun normal() {
        something(3)
    }

    fun something(action: () -> Unit, input: Int) = Unit

    @Test
    fun lambda() {
        something({ 2 * 3 }, 3)
    }

    fun something(input: Int, action: () -> Unit) = Unit

    @Test
    fun parentheses() {
        something(3, { 2 * 3 })
        something(3) {
            2 * 3
        }
    }

    fun first(init: First.() -> Unit) = Unit
    class First {
        fun then(init: Then.() -> Unit) = Unit
        class Then {
            fun followedBy(init: FollowedBy.() -> Unit) = Unit
            class FollowedBy
        }
    }

    @Test
    fun receivers() {
        first {
            then {
                followedBy {
                }
            }
        }
    }

    @Test
    fun router() {
        val r =  route {
            get("/api/v2") {
                json(Data(1, 5))
            }
            post("/api/v2") {
                xml(it.body)
            }
            put("/api") { request ->
                request.query["version"]
                empty()
            }
        }
    }

    private fun route(init: Route.() -> Unit) = Route().also(init)

    class Route {
        private var routes = mutableMapOf<Pair<String, String>, Action.(r: Request) -> Response>()

        fun get(route: String, action: Action.(r: Request) -> Response) =
            action.also { routes[Pair(route, "get")] = it }

        fun post(route: String, action: Action.(r: Request) -> Response) =
            action.also { routes[Pair(route, "post")] = it }

        fun put(route: String, action: Action.(r: Request) -> Response) =
            action.also { routes[Pair(route, "put")] = it }

        fun delete(route: String, action: Action.(r: Request) -> Response) =
            action.also { routes[Pair(route, "delete")] = it }
    }

    class Request {
        val query = mapOf<String, String>()
        val body = ""
    }

    class Response

    class Action {
        fun <T> json(data: T) = Response()
        fun <T> xml(data: T) = Response()
        fun <T> text(data: T) = Response()
        fun empty() = Response()
    }

    data class Data(val id: Int, val count: Int)
}