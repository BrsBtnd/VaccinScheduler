import { applyMiddleware, compose, createStore } from 'redux';
import { persistStore, persistReducer } from 'redux-persist';
import storage from 'redux-persist/lib/storage';

import logger from 'redux-logger';

import { rootReducer } from './rootReducer';

const persistConfig = {
  key: 'root',
  storage,
};

const persistedReducer = persistReducer(persistConfig, rootReducer);

// const composeEnhancers = compose(applyMiddleware(logger));
// export const store = createStore(persistedReducer, undefined, composeEnhancers);
export const store = createStore(persistedReducer);

export const persistor = persistStore(store);
