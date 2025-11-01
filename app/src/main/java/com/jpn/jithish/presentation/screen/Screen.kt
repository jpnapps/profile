package com.jpn.jithish.presentation.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val label: String, val icon: ImageVector?) {
    object Favorites : Screen("favorites", "Favorites", Icons.Default.Favorite)
    object Notes : Screen("notes", "Notes", Icons.Default.Edit)
    object Profile : Screen("profile", "Profile", Icons.Default.Person)
    object Routine : Screen("routine", "Routine", Icons.Default.Settings)
   // object Settings : Screen("settings", "Settings", Icons.Default.Settings)
    object Pwd : Screen("pwd", "Password", Icons.Default.Settings)
    object More : Screen("more", "More", Icons.Default.MoreVert)
    object StudyNotes : Screen("study", "study", Icons.Default.Settings)
    object Theme : Screen("theme", "theme", Icons.Default.Edit)
    object Vault : Screen("vault", "vault", Icons.Default.Lock)
}

//intenal
/*

internal interface B{

}
internal abstract class C{

}
internal class D{

}
class A {
    internal  interface X{

    }
    internal  abstract class Y{
        abstract fun anD()
    }
    internal class D{

    }
}

// public

public interface BC{

}
public abstract class CC{

}
public class DD{

}
class AB {
    public  interface XX{

    }
    public  abstract class Y{
        abstract fun anD()
    }
    public  class D{

    }
}

//default public
 interface BCE{

}
 abstract class CCCC{

}
 class DDD{

}
class ABB {
      interface XX{

    }
      abstract class Y{
        abstract fun anD()
    }
      class D{

    }
}

//protected

protected interface BCCC{

}
protected abstract class CCCCC{

}
protected class DDDE{

}
class ABcCD {
    protected interface XXX{

    }
    protected abstract class YY{
        abstract fun anD()
    }
    protected class DD{

    }
}


//protected

protected interface BCC{

}
protected abstract class CCC{

}
protected class DDE{

}
class ABc {
    protected interface XXX{

    }
    protected abstract class YY{
        abstract fun anD()
    }
    protected class DD{

    }
}*/
