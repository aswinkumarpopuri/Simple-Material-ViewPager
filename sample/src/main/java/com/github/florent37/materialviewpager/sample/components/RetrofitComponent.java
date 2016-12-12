package com.github.florent37.materialviewpager.sample.components;

import com.github.florent37.materialviewpager.sample.modules.RetrofitModule;

import dagger.Component;
import dagger.Module;

/**
 * Created by raian on 12/12/16.
 */

@Component(modules = {RetrofitModule.class})
public interface RetrofitComponent {
    void inject();
}
