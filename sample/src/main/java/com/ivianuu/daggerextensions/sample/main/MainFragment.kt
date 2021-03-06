package com.ivianuu.daggerextensions.sample.main

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import com.ivianuu.daggerextensions.sample.child.ChildFragment
import com.ivianuu.daggerextensions.sample.deps.ActivityDependency
import com.ivianuu.daggerextensions.sample.deps.AppDependency
import com.ivianuu.daggerextensions.sample.deps.FragmentDependency
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * @author Manuel Wrage (IVIanuu)
 */
class MainFragment : Fragment(), HasSupportFragmentInjector {

    @Inject lateinit var appDependency: AppDependency
    @Inject lateinit var activityDependency: ActivityDependency
    @Inject lateinit var fragmentDependency: FragmentDependency
    @Inject lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)

        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        childFragmentManager.beginTransaction()
            .add(ChildFragment(), "child")
            .commitNow()
    }

    override fun supportFragmentInjector() = supportFragmentInjector

}