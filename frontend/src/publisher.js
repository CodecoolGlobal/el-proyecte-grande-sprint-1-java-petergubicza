class Publisher {

  constructor() {
    this.subscribers = new Set();
  }

  /// It's like addEventListener
  ///
  /// Subscriber must be a function
  addSubscriber(subscriber) {
    this.subscribers.add(subscriber);
  }

  removeSubscriber(subscriber) {
    this.subscribers.delete(subscriber);
  }

  broadcastEvent() {
    for (const subscriber of this.subscribers) {
      subscriber();
    };
  };
}

export default Publisher;